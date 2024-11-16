package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.exception.CurrencyMismatchException;
import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.repository.IOperationRepository;
import by.it_academy.jd2.repository.entity.AccountEntity;
import by.it_academy.jd2.repository.entity.OperationEntity;
import by.it_academy.jd2.service.api.IAccountService;
import by.it_academy.jd2.service.api.IOperationService;
import by.it_academy.jd2.service.api.IUserHolderService;
import by.it_academy.jd2.service.dto.OperationCreateDto;
import by.it_academy.jd2.service.dto.OperationReadDto;
import by.it_academy.jd2.service.dto.OperationUpdateDto;
import by.it_academy.jd2.service.mapper.IOperationMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Validated
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OperationService implements IOperationService {

    private final IOperationRepository operationRepository;
    private final IOperationMapper operationMapper;
    private final IAccountService accountService;
    private final IUserHolderService userHolderService;

    @Transactional
    public void create(@Valid OperationCreateDto createDto, UUID accountId) {

        AccountEntity account = accountService
                .findEntityByIdAndUserId(accountId)
                .orElseThrow(IdNotFoundException::new);

        if (!checkCurrency(createDto, account)) {
            throw new CurrencyMismatchException();
        }

        Optional.of(createDto)
                .map(dto -> operationMapper.mapCreate(createDto, account))
                .map(operationRepository::saveAndFlush)
                .orElseThrow();
    }

    @Override
    public PageOf<OperationReadDto> findAll(@Valid PageDto pageDto, UUID accountId) {
        UUID currentUserId = userHolderService.getUserId();

        Sort sortOperation = Sort.sort(OperationEntity.class)
                .by(OperationEntity::getDtCreate)
                .descending();

        PageRequest pageRequest = PageRequest.of(
                pageDto.getPage(),
                pageDto.getSize(),
                sortOperation);

        Page<OperationReadDto> operations = operationRepository
                .findAllByAccountIdAndUserId(accountId, currentUserId, pageRequest)
                .map(operationMapper::mapRead);

        return PageOf.of(operations);
    }

    @Override
    @Transactional
    public void update(@Valid OperationCreateDto dto,
                       @Valid OperationUpdateDto updateDto) {
        UUID currentUserId = userHolderService.getUserId();

        OperationEntity operationEntity = getOperationEntity(updateDto, currentUserId);

        if (!checkCurrency(dto, operationEntity.getAccountEntity())) {
            throw new CurrencyMismatchException();
        }

        Optional.of(operationEntity)
                .map(entity -> operationMapper.mapUpdate(dto, entity))
                .map(operationRepository::saveAndFlush)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void delete(@Valid OperationUpdateDto updateDto) {
        UUID currentUserId = userHolderService.getUserId();

        OperationEntity operationEntity = getOperationEntity(updateDto, currentUserId);

        updateBalance(operationEntity);
        operationRepository.delete(operationEntity);
        operationRepository.flush();
    }

    private OperationEntity getOperationEntity(OperationUpdateDto updateDto, UUID currentUserId) {
        return operationRepository
                .findByIdAndUserId(updateDto.getOperationId(), currentUserId)
                .orElseThrow(IdNotFoundException::new);
    }

    @Override
    public Optional<OperationReadDto> findByIdAndAccountId(UUID id, UUID accountId) {
        return operationRepository.findByIdAndAccountId(id, accountId)
                .map(operationMapper::mapRead);
    }

    private void updateBalance(OperationEntity operationEntity) {
        BigDecimal value = operationEntity.getValue();
        AccountEntity accountEntity = operationEntity.getAccountEntity();
        accountEntity.setBalance(accountEntity.getBalance().subtract(value));
    }

    private boolean checkCurrency(OperationCreateDto dto, AccountEntity account) {
        return account.getCurrencyId().equals(dto.getCurrencyId());
    }
}

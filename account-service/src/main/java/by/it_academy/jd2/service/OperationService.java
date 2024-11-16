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

    @Transactional
    public void create(@Valid OperationCreateDto createDto, UUID accountId) {

        AccountEntity account = accountService
                .findEntityById(accountId)
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
    public PageOf<OperationReadDto> findAll(@Valid PageDto pageDto, UUID accountId) {          //надо проверить принадлежность аккаунт Id к пользователю

        Sort sortOperation = Sort.sort(OperationReadDto.class)
                .by(OperationReadDto::getDtCreate)
                .descending();

        PageRequest pageRequest = PageRequest.of(
                pageDto.getPage(),
                pageDto.getSize(),
                sortOperation);

        Page<OperationReadDto> operations = operationRepository.findAllByAccountId(accountId, pageRequest)
                .map(operationMapper::mapRead);

        return PageOf.of(operations);
    }

    @Override
    @Transactional
    public void update(@Valid OperationCreateDto dto,                                            //надо проверить принадлежность аккаунт Id к пользователю
                       @Valid OperationUpdateDto updateDto) {

        OperationEntity operationEntity = operationRepository
                .findById(updateDto.getOperationId())
                .orElseThrow(IdNotFoundException::new);

        if (!checkCurrency(dto, operationEntity.getAccountEntity())) {
            throw new CurrencyMismatchException();
        }

        Optional.of(operationEntity)
                .map(entity -> operationMapper.mapUpdate(dto, entity))
                .map(operationRepository::saveAndFlush)
                .orElseThrow();
    }

    @Override
    public Optional<OperationReadDto> findByIdAndAccountId(UUID id, UUID accountId) {
        return operationRepository.findByIdAndAccountId(id, accountId)
                .map(operationMapper::mapRead);
    }

    @Override
    @Transactional
    public void delete(@Valid OperationUpdateDto updateDto) {                                       //надо проверить принадлежность аккаунт Id к пользователю
        operationRepository
                .findById(updateDto.getOperationId())
                .map(operationEntity -> {
                    updateBalance(operationEntity);
                    operationRepository.delete(operationEntity);
                    operationRepository.flush();
                    return true;
                }).orElseThrow();
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

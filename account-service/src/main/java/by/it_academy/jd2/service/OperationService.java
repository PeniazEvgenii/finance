package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.aop.LoggingAspect;
import by.it_academy.jd2.commonlib.dto.PageDto;
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
import by.it_academy.jd2.service.feign.api.IAuditService;
import by.it_academy.jd2.service.mapper.api.IOperationMapper;
import by.it_academy.jd2.service.validation.api.IValidateOperation;
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

import static by.it_academy.jd2.commonlib.constant.Actions.*;

@LoggingAspect
@Service
@Validated
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OperationService implements IOperationService {

    private final IOperationRepository operationRepository;
    private final IUserHolderService userHolderService;
    private final IValidateOperation validateOperation;
    private final IOperationMapper operationMapper;
    private final IAccountService accountService;
    private final IAuditService auditService;

    @Transactional
    public void create(@Valid OperationCreateDto createDto, UUID accountId) {

        AccountEntity account = accountService
                .findByIdAndUserId(accountId);

        validateOperation.validateCreate(createDto, account);

        Optional.of(createDto)
                .map(dto -> operationMapper.mapCreate(dto, account))
                .map(operationRepository::saveAndFlush)
                .ifPresent(operation -> auditService.send(AUDIT_OPERATION_CREATE, operation.getId()));
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
    public void update(@Valid OperationCreateDto createDto,
                       @Valid OperationUpdateDto updateDto) {

        OperationEntity operationEntity = findByIdAndUserId(updateDto.getOperationId());

        validateOperation.validateUpdate(createDto, updateDto, operationEntity);
        updateBalance(operationEntity, createDto.getValue());

        Optional.of(operationEntity)
                .map(entity -> operationMapper.mapUpdate(createDto, entity))
                .map(operationRepository::saveAndFlush)
                .ifPresent(operation -> auditService.send(AUDIT_OPERATION_UPDATE, operation.getId()));
    }

    @Override
    @Transactional
    public void delete(@Valid OperationUpdateDto updateDto) {

        OperationEntity operationEntity = findByIdAndUserId(updateDto.getOperationId());

        validateOperation.validateDelete(updateDto, operationEntity);
        updateBalance(operationEntity, BigDecimal.ZERO);

        operationRepository.delete(operationEntity);
        operationRepository.flush();

        auditService.send(AUDIT_OPERATION_DELETE, updateDto.getOperationId());
    }

    @Override
    public OperationEntity findByIdAndUserId(UUID id) {
        UUID currentUserId = userHolderService.getUserId();

        return operationRepository
                .findByIdAndUserId(id, currentUserId)
                .orElseThrow(IdNotFoundException::new);
    }

    private void updateBalance(OperationEntity entity, BigDecimal newValue) {

        AccountEntity accountEntity = entity.getAccountEntity();
        BigDecimal oldBalance = accountEntity.getBalance();
        BigDecimal newBalance = oldBalance.subtract(entity.getValue()).add(newValue);
        accountEntity.setBalance(newBalance);
    }

    @Transactional
    @Override
    public Optional<OperationReadDto> saveSchedule(OperationCreateDto createDto, UUID accountId) {
        AccountEntity account = accountService.findEntityById(accountId);
        return Optional.of(createDto)
                .map(dto -> operationMapper.mapCreate(dto, account))
                .map(operationRepository::saveAndFlush)
                .map(operationMapper::mapRead);
    }

}

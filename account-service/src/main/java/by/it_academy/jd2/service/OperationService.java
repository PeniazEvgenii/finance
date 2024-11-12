package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.repository.IOperationRepository;
import by.it_academy.jd2.repository.entity.AccountEntity;
import by.it_academy.jd2.service.api.IOperationService;
import by.it_academy.jd2.service.dto.OperationCreateDto;
import by.it_academy.jd2.service.dto.OperationDto;
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

    @Transactional
    public void create(@Valid OperationCreateDto createDto) {
        /**
         * 1 Надо аккаунт(счет) проверить, что есть       +-  что то на маппере есть
         * Что аккаунт  нужному челу принадлежит          -
         * 2 Надо сравнить валюту на счете и в операции  -+ на маппере
         * 3 ? валюта проверялась при создании счета, надо ли при каждой операции запрос в Classifier +    УДАЛИТЬ ИЗ ПРОВЕРКИ ЗАПРОС -
         * 4 КАТЕГОРИЮ НАДО ЗАПРОСИТЬ ЧТО ПО ИД ЕСТЬ!!!!   +
         * 5 сделали операцию и изменить счет надо         +
         */

        Optional.of(createDto)
                .map(operationMapper::mapCreate)
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
    public void update(@Valid OperationDto dto,
                       @Valid OperationUpdateDto updateDto) {
        /**
         * Что аккаунт  нужному челу принадлежит          -
         * 0 Надо операции проверить, что есть            +
         * 1 Надо аккаунт(счет) проверить, что есть       + и 1 и второе сразу и время проверил в одной аннотации
         * 2 Надо сравнить валюту на счете и в операции   + сравнил в маппере
         * 4 КАТЕГОРИЮ НАДО ЗАПРОСИТЬ ЧТО ПО ИД ЕСТЬ!!!!  +
         */

        operationRepository
                .findById(updateDto.getOperationId())
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
    public void delete(@Valid OperationUpdateDto updateDto) {
        operationRepository.findById(updateDto.getOperationId())
                .map(operationEntity -> {
                    BigDecimal value = operationEntity.getValue();
                    AccountEntity accountEntity = operationEntity.getAccountEntity();
                    accountEntity.setBalance(accountEntity.getBalance().subtract(value));
                    operationRepository.delete(operationEntity);
                    operationRepository.flush();
                    return true;
                }).orElseThrow();
    }
}

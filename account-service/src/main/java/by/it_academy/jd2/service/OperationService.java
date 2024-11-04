package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.exception.CurrencyMismatchException;
import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.commonlib.exception.UpdateTimeMismatchException;
import by.it_academy.jd2.repository.IOperationRepository;
import by.it_academy.jd2.repository.entity.AccountEntity;
import by.it_academy.jd2.repository.entity.OperationEntity;
import by.it_academy.jd2.service.api.IAccountService;
import by.it_academy.jd2.service.dto.OperationCreateDto;
import by.it_academy.jd2.service.dto.UpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OperationService implements IOperationService {

    private final IOperationRepository operationRepository;
    private final IAccountService accountService;
    private final RestTemplate restTemplate;

    @Transactional
    public void create(OperationCreateDto createDto, UUID accountId) {
        /**
         * 1 Надо аккаунт(счет) проверить, что есть
         * 2 Надо сравнить валюту на счете и в операции
         * 3 ? валюта проверялась при создании счета, надо ли при каждой операции запрос в Classifier
         * 4 КАТЕГОРИЮ НАДО ЗАПРОСИТЬ ЧТО ПО ИД ЕСТЬ!!!!
         *
         * 5 сделали операцию и изменить счет надо
         */

        AccountEntity accountEntity = getAccount(accountId);
       // accountEntity.getBalance().add(createDto.getValue());

        if(checkCurrency(createDto, accountEntity)) {
            throw new CurrencyMismatchException();
        }

        OperationEntity operationEntity = OperationEntity.builder()
                .date(createDto.getDate())
                .description(createDto.getDescription())
                .categoryId(createDto.getCategory())
                .value(createDto.getValue())
                .accountEntity(accountEntity)
                .build();

        operationRepository.saveAndFlush(operationEntity);

    }

    public void update(OperationCreateDto createDto, UpdateDto updateDto) {
        /**
         * 0 Надо операции проверить, что есть
         * 1 Надо аккаунт(счет) проверить, что есть
         * 2 Надо сравнить валюту на счете и в операции
         * 3 ? валюта проверялась при создании счета, надо ли при каждой операции запрос в Classifier
         * 4 КАТЕГОРИЮ НАДО ЗАПРОСИТЬ ЧТО ПО ИД ЕСТЬ!!!!
         */

        OperationEntity operationEntity = operationRepository.findById(updateDto.getOperationId())
                .orElseThrow(IdNotFoundException::new);

        if(!updateDto.getAccountId().equals(operationEntity.getAccountEntity().getId())) {
            throw new IdNotFoundException();
        }

        if(checkCurrency(createDto, operationEntity.getAccountEntity())) {
            throw new CurrencyMismatchException();
        }


        if(!updateDto.getDtUpdate().equals(operationEntity.getDtUpdate())) {
            throw new UpdateTimeMismatchException();
        }

        operationEntity.setDate(createDto.getDate());
        operationEntity.setDescription(createDto.getDescription());
        operationEntity.setCategoryId(createDto.getCategory());
        operationEntity.setValue(createDto.getValue());
        //operationEntity.setCurrency

    }






    private AccountEntity getAccount(UUID accountId) {
        return accountService
                .findEntityById(accountId)
                .orElseThrow(IdNotFoundException::new);
    }

    private boolean checkCurrency(OperationCreateDto createDto, AccountEntity entity) {
        return entity.getCurrencyId() != createDto.getCurrency();
    }
}

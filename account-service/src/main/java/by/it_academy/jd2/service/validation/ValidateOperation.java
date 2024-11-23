package by.it_academy.jd2.service.validation;

import by.it_academy.jd2.commonlib.exception.CurrencyMismatchException;
import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.commonlib.exception.UpdateTimeMismatchException;
import by.it_academy.jd2.repository.entity.AccountEntity;
import by.it_academy.jd2.repository.entity.OperationEntity;
import by.it_academy.jd2.service.dto.OperationCreateDto;
import by.it_academy.jd2.service.dto.OperationUpdateDto;
import by.it_academy.jd2.service.validation.api.IValidateOperation;
import org.springframework.stereotype.Component;

@Component
public class ValidateOperation implements IValidateOperation {

    public void validateCreate(OperationCreateDto createDto, AccountEntity account) {

        if (!isCurrencyMatching(createDto, account)) {
            throw new CurrencyMismatchException();
        }
    }

    public void validateUpdate(OperationCreateDto createDto,
                               OperationUpdateDto updateDto,
                               OperationEntity operationEntity) {

        validateDelete(updateDto, operationEntity);
        if(!isCurrencyMatching(createDto, operationEntity.getAccountEntity())) {
            throw new CurrencyMismatchException();

        }
    }

    public void validateDelete(OperationUpdateDto updateDto, OperationEntity operationEntity) {

        AccountEntity accountEntity = operationEntity.getAccountEntity();
        if(!isAccountIdMatchingOperationId(updateDto, accountEntity)) {
            throw new IdNotFoundException("Id операции не соответствует id счета");
        }

        if(!isUpdateTimeMatching(updateDto, operationEntity)) {
            throw new UpdateTimeMismatchException();
        }

    }

    private boolean isAccountIdMatchingOperationId(OperationUpdateDto updateDto, AccountEntity accountEntity) {
        return accountEntity.getId().equals(updateDto.getAccountId());
    }

    private boolean isCurrencyMatching(OperationCreateDto dto, AccountEntity account) {
        return account.getCurrencyId().equals(dto.getCurrencyId());
    }

    private boolean isUpdateTimeMatching(OperationUpdateDto updateDto, OperationEntity operationEntity) {
        return operationEntity.getDtUpdate().equals(updateDto.getDtUpdate());
    }

}
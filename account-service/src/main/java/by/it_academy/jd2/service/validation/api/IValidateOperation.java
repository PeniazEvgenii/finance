package by.it_academy.jd2.service.validation.api;

import by.it_academy.jd2.repository.entity.AccountEntity;
import by.it_academy.jd2.repository.entity.OperationEntity;
import by.it_academy.jd2.service.dto.OperationCreateDto;
import by.it_academy.jd2.service.dto.OperationUpdateDto;

public interface IValidateOperation {

    void validateCreate(OperationCreateDto createDto,
                        AccountEntity account);

    void validateUpdate(OperationCreateDto createDto,
                        OperationUpdateDto updateDto,
                        OperationEntity operationEntity);

    void validateDelete(OperationUpdateDto updateDto,
                        OperationEntity operationEntity);
}

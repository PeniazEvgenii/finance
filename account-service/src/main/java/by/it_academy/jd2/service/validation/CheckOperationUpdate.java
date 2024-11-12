package by.it_academy.jd2.service.validation;

import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.service.IOperationService;
import by.it_academy.jd2.service.dto.OperationReadDto;
import by.it_academy.jd2.service.dto.OperationUpdateDto;
import by.it_academy.jd2.service.validation.annotation.OperationUpdate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckOperationUpdate implements ConstraintValidator<OperationUpdate, OperationUpdateDto> {

    private final IOperationService operationService;

    @Override
    public boolean isValid(OperationUpdateDto value, ConstraintValidatorContext context) {
        OperationReadDto operation = operationService
                .findByIdAndAccountId(value.getOperationId(), value.getAccountId())
                .orElseThrow(IdNotFoundException::new);

        return operation.getDtUpdate().equals(value.getDtUpdate());
    }
}

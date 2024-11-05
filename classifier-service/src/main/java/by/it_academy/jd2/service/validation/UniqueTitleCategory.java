package by.it_academy.jd2.service.validation;

import by.it_academy.jd2.service.api.IOperationCategoryService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueTitleCategory implements ConstraintValidator<UniqueCategory, String> {

    private final IOperationCategoryService operationCategoryService;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext constraintValidatorContext) {
        return operationCategoryService.findByTitle(title)
                .isEmpty();
    }
}

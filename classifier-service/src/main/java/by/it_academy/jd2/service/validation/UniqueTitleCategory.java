package by.it_academy.jd2.service.validation;

import by.it_academy.jd2.service.IClassifierService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueTitleCategory implements ConstraintValidator<UniqueCategory, String> {

    private final IClassifierService classifierService;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext constraintValidatorContext) {
        return classifierService.findOperationCategoryByTitle(title)
                .isEmpty();
    }
}

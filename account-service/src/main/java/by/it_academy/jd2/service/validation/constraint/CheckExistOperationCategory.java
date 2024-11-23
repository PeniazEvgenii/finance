package by.it_academy.jd2.service.validation.constraint;

import by.it_academy.jd2.service.feign.api.IClassifierService;
import by.it_academy.jd2.service.validation.annotation.ExistCategory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CheckExistOperationCategory implements ConstraintValidator<ExistCategory, UUID> {

    private final IClassifierService classifierService;

    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext context) {
        return classifierService.findOperationCategoryById(value)
                .isPresent();
    }
}

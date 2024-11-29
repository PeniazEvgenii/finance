package by.it_academy.jd2.schedulerservice.service.validation.constraint;

import by.it_academy.jd2.schedulerservice.service.feign.api.IClassifierService;
import by.it_academy.jd2.schedulerservice.service.validation.annotation.ExistCurrency;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CheckExistCurrency implements ConstraintValidator<ExistCurrency, UUID> {

    private final IClassifierService classifierService;

    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext context) {
        return classifierService.findCurrencyById(value)
                .isPresent();
    }
}

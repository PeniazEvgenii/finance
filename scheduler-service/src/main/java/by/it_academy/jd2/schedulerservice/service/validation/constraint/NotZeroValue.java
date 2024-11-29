package by.it_academy.jd2.schedulerservice.service.validation.constraint;

import by.it_academy.jd2.schedulerservice.service.validation.annotation.NotZero;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class NotZeroValue implements ConstraintValidator<NotZero, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        return value.compareTo(BigDecimal.ZERO) != 0;
    }
}

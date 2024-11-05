package by.it_academy.jd2.service.validation;

import by.it_academy.jd2.service.api.ICurrencyService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueTitleCurrency implements ConstraintValidator<UniqueCurrency, String> {

    private final ICurrencyService currencyService;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext constraintValidatorContext) {
        return currencyService.findByTitle(title)
                .isEmpty();
    }
}

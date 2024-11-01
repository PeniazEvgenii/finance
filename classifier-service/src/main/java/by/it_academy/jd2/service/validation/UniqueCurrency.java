package by.it_academy.jd2.service.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueTitleCurrency.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCurrency {
    String message() default "{Указанная валюта уже зарегистрирована в системе}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

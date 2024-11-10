package by.it_academy.jd2.service.validation.annotation;

import by.it_academy.jd2.service.validation.CheckExistCurrency;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CheckExistCurrency.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistCurrency {
    String message() default "{Неверна указана валюта}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

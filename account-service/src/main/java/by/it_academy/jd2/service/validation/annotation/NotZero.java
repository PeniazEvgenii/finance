package by.it_academy.jd2.service.validation.annotation;

import by.it_academy.jd2.service.validation.constraint.NotZeroValue;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NotZeroValue.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotZero {
    String message() default "{Сумма операции не должна быть равна нулю}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

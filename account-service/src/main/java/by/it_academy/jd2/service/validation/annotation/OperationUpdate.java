package by.it_academy.jd2.service.validation.annotation;

import by.it_academy.jd2.service.validation.CheckOperationUpdate;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CheckOperationUpdate.class)
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationUpdate {
    String message() default "{Неверно указано время обновления}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

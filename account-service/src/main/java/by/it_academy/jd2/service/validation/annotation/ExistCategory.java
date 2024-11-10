package by.it_academy.jd2.service.validation.annotation;

import by.it_academy.jd2.service.validation.CheckExistOperationCategory;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CheckExistOperationCategory.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistCategory {
    String message() default "{Неверна указана категория операции}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package by.it_academy.jd2.service.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserMailUpdate.class)
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MailUpdate {
    String message() default "{Указанный email уже зарегистрирован в системе}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

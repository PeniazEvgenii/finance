package by.it_academy.jd2.service.validation;

import by.it_academy.jd2.service.api.IUserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMailUnique implements ConstraintValidator<UniqueMail, String> {

    private final IUserService userService;

    @Override
    public boolean isValid(String mail, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findByMail(mail)
                .isEmpty();
    }
}

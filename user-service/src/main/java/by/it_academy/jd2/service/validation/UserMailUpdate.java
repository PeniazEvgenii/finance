package by.it_academy.jd2.service.validation;

import by.it_academy.jd2.service.UserService;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.dto.UserUpdateDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMailUpdate implements ConstraintValidator<MailUpdate, UserUpdateDto> {

    private final UserService userService;

    @Override
    public boolean isValid(UserUpdateDto userUpdateDto, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findByMail(userUpdateDto.getMail())
                .map(UserReadDto::getUuid)
                .filter(uuid -> !uuid.equals(userUpdateDto.getId()))
                .isEmpty();
    }
}

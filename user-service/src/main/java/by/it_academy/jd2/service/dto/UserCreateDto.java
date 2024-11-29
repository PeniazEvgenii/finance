package by.it_academy.jd2.service.dto;

import by.it_academy.jd2.commonlib.dto.EUserRole;
import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.service.validation.UniqueMail;
import by.it_academy.jd2.service.validation.group.CreateAction;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
public class UserCreateDto {
    @UniqueMail(groups = {CreateAction.class})
    @Email(message = "Некорректный формат электронной почты. Mail должен иметь формат user@example.com")
    @NotBlank(message = "Электронная почта обязательна")
    private final String mail;

    @NotNull(message = "ФИО обязателен")
    @NotBlank(message = "ФИО обязательно к заполнению и не должно быть пустым")
    private final String fio;

    @NotNull(message = "Роль пользователя обязательна")
    private final EUserRole role;

    @NotNull(message = "Статус пользователя обязателен")
    private final EUserStatus status;

    @NotNull(message = "Пароль обязателен")
    @NotBlank(message = "Пароль должен содержать не менее 4 символов")
    @Length(min = 4, message = "Длина пароля должна быть не менее 4 символов")
    private final String password;
}

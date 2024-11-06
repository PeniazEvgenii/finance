package by.it_academy.jd2.service.dto;

import by.it_academy.jd2.commonlib.dto.UserRole;
import by.it_academy.jd2.service.validation.MailUpdate;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@MailUpdate
public class UserUpdateDto {

//    @Email(message = "Некорректный формат электронной почты. Email должен иметь формат user@example.com")
//    @NotBlank(message = "Электронная почта обязательна")
    private final String mail;

//    @NotNull(message = "ФИО обязателен")
//    @NotBlank(message = "ФИО должен быть не пустой")
    private final String fio;

//    @NotNull(message = "Роль пользователя обязательна")
    private final UserRole role;

//    @NotNull(message = "Статус пользователя обязателен")
    private final UserStatus status;

//    @NotNull(message = "Пароль обязателен")
//    @NotBlank(message = "Пароль должен быть не пустой")
    private final String password;

    private final UUID id;

    private final Instant dtUpdate;
}

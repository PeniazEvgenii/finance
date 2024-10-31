package by.it_academy.jd2.service.dto;

import by.it_academy.jd2.service.validation.UniqueMail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRigistrationDto {
    @UniqueMail
    @Email(message = "Некорректный формат электронной почты. Email должен иметь формат user@example.com")
    private final String mail;

    @NotNull(message = "ФИО обязателен")
    @NotBlank(message = "ФИО должен быть не пустой")
    private final String fio;

    @NotNull(message = "Пароль обязателен")
    @NotBlank(message = "Пароль должен быть не пустой")
    @Length(min = 4, message = "Длина пароля должна быть не менне 4 цифр")
    private final String password;
}

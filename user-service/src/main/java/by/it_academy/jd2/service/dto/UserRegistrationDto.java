package by.it_academy.jd2.service.dto;

import by.it_academy.jd2.service.validation.UniqueMail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRegistrationDto {
    @UniqueMail
    @Email(message = "Некорректный формат электронной почты. Mail должен иметь формат user@example.com")
    @NotBlank(message = "Электронная почта обязательна")
    private final String mail;

    @NotNull(message = "ФИО обязателен")
    @NotBlank(message = "ФИО обязательно к заполнению и не должно быть пустым")
    private final String fio;

    @NotNull(message = "Пароль обязателен")
    @NotBlank(message = "Пароль должен содержать не менее 4 символов")
    @Length(min = 4, message = "Длина пароля должна быть не менее 4 символов")
    private final String password;
}

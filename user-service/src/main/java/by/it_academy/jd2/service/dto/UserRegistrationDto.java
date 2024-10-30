package by.it_academy.jd2.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRegistrationDto {
    @Email(message = "Некорректный формат электронной почты. Email должен иметь формат user@example.com")
    @NotBlank(message = "Электронная почта обязательна")
    private final String mail;

    @NotNull(message = "ФИО обязателен")
    @NotBlank(message = "ФИО должен быть не пустой")
    private final String fio;

    @NotNull(message = "Пароль обязателен")
    @NotBlank(message = "Пароль должен быть не пустой")          //min можно добавить
    private final String password;
}

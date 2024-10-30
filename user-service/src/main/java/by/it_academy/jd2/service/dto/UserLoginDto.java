package by.it_academy.jd2.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginDto {
    @Email(message = "Некорректный формат электронной почты")
    @NotBlank(message = "Электронная почта обязательна")
    private final String mail;

    @NotNull(message = "Пароль обязателен")
    @NotBlank(message = "Пароль должен быть не пустой")
    private final String password;
}

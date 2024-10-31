package by.it_academy.jd2.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VerificationDto {

    @NotNull(message = "Код обязателен")
    @NotBlank(message = "Код должен быть не пустой")
    private final String code;

    @Email(message = "Некорректный формат электронной почты. Email должен иметь формат user@example.com")
    @NotBlank(message = "Электронная почта обязательна")
    private final String mail;
}

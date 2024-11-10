package by.it_academy.jd2.service.dto;

import by.it_academy.jd2.service.validation.annotation.ExistCurrency;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AccountCreateDto {

    @NotNull(message = "Название счета обязателено")
    @NotBlank(message = "Название счета не должно быть пустым")
    private final String title;

    @NotNull(message = "Описание счета обязателено")
    @NotBlank(message = "Описание счета не должно быть пустым")
    private final String description;

    @NotNull(message = "Тип счета обязателен")
    private final EType type;

    @ExistCurrency
    @NotNull(message = "Валюта обязателена")  // можно в аннотации делать запрос, что такая валюта есть!!!
    @JsonProperty(value = "currency")
    private final UUID currencyId;
}

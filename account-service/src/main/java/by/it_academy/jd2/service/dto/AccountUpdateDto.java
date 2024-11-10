package by.it_academy.jd2.service.dto;

import by.it_academy.jd2.service.validation.annotation.AccountUpdate;
import by.it_academy.jd2.service.validation.annotation.ExistCurrency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AccountUpdate
public class AccountUpdateDto {

    @NotNull(message = "uuid счета обязателен")
    private final UUID id;

    @NotNull(message = "Время обновления обязательно")
    private final Instant dtUpdate;

    @NotNull(message = "Название счета обязателено")
    @NotBlank(message = "Название счета не должно быть пустым")
    private final String title;

    @NotNull(message = "Описание счета обязателено")
    @NotBlank(message = "Описание счета не должно быть пустым")
    private final String description;

    @NotNull(message = "Тип счета обязателен")
    private final EType type;

    @ExistCurrency
    @NotNull(message = "Валюта обязателена")
    private final UUID currencyId;
}

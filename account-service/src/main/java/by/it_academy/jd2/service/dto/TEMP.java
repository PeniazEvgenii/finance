package by.it_academy.jd2.service.dto;

import by.it_academy.jd2.service.validation.annotation.ExistCategory;
import by.it_academy.jd2.service.validation.annotation.ExistCurrency;
import by.it_academy.jd2.service.validation.annotation.NotZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class TEMP {

    @NotNull(message = "Указание счета обязательно")
    private final UUID accountId;

    @NotNull(message = "Указание операции обязательно")
    private final UUID operationId;

    @NotNull(message = "Дата обновления обязательна")
    private final Instant dtUpdate;

    @NotNull(message = "Дата операции обязателена")
    private final Instant date;

    @NotNull(message = "Описание операции обязателено")
    @NotBlank(message = "Описание операции не должно быть пустым")
    private final String description;

    @ExistCategory
    @NotNull(message = "Категория операции обязателена")
    private final UUID categoryId;

    @NotZero
    @NotNull(message = "Сумма операции обязателена")
    private final BigDecimal value;

    @ExistCurrency                                               //удалить и заменить на общую аннотацию сравнение валюты на счете и id пользователя
    @NotNull(message = "указание валюты обязателено")
    private final UUID currencyId;
}

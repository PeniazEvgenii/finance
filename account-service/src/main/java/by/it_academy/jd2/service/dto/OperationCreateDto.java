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
public class OperationCreateDto {

    @NotNull(message = "Указание счета обязательно")
    private UUID accountId;

    @NotNull(message = "Дата операции обязателена")
    private Instant date;

    @NotNull(message = "Описание операции обязателено")
    @NotBlank(message = "Описание операции не должно быть пустым")
    private String description;

    @ExistCategory
    @NotNull(message = "Категория операции обязателена")
    private UUID categoryId;

    @NotNull(message = "Сумма операции обязателена")
    @NotZero
    private BigDecimal value;

    // @ExistCurrency   есть в маппере сравнение с валютой счета
    @NotNull(message = "указание валюты обязателено")
    private UUID currencyId;
}

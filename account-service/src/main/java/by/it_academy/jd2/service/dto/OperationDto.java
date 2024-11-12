package by.it_academy.jd2.service.dto;

import by.it_academy.jd2.service.validation.annotation.ExistCategory;
import by.it_academy.jd2.service.validation.annotation.ExistCurrency;
import by.it_academy.jd2.service.validation.annotation.NotZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class OperationCreateDto {  //OperationDto

    @NotNull(message = "Дата операции обязателена")
    private final Instant date;

    @NotNull(message = "Описание операции обязателено")
    @NotBlank(message = "Описание операции не должно быть пустым")
    private final String description;

    @ExistCategory
    @NotNull(message = "Категория операции обязателена")
    private final UUID category;

    @NotNull(message = "Сумма операции обязателена")
    @NotZero
    private final BigDecimal value;

    @ExistCurrency
    @NotNull(message = "указание валюты обязателено")
    private final UUID currency;


}

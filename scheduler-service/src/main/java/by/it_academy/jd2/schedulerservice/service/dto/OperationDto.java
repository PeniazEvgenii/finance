package by.it_academy.jd2.schedulerservice.service.dto;

import by.it_academy.jd2.schedulerservice.service.validation.annotation.ExistCategory;
import by.it_academy.jd2.schedulerservice.service.validation.annotation.NotZero;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OperationDto {

    @NotNull(message = "Счет обязателен")
    @JsonProperty(value = "account")
    private final UUID accountId;

    @ExistCategory
    @NotNull(message = "Категория операции обязателена")
    @JsonProperty(value = "category")
    private final UUID categoryId;

    @NotNull(message = "Описание операции обязателено")
    @NotBlank(message = "Описание операции не должно быть пустым")
    private final String description;

    @NotZero
    @NotNull(message = "Сумма операции обязателена")
    private final BigDecimal value;

    @NotNull(message = "указание валюты обязателено")
    @JsonProperty(value = "currency")
    private final UUID currencyId;
}

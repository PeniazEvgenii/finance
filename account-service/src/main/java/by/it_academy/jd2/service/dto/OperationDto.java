package by.it_academy.jd2.service.dto;

import by.it_academy.jd2.service.validation.annotation.ExistCategory;
import by.it_academy.jd2.service.validation.annotation.ExistCurrency;
import by.it_academy.jd2.service.validation.annotation.NotZero;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class OperationDto {  //OperationDto

    @NotNull(message = "Дата операции обязателена")
    private final Instant date;

    @NotNull(message = "Описание операции обязателено")
    @NotBlank(message = "Описание операции не должно быть пустым")
    private final String description;

    @ExistCategory
    @NotNull(message = "Категория операции обязателена")
    @JsonProperty(value = "category")
    private final UUID categoryId;

    @NotZero
    @NotNull(message = "Сумма операции обязателена")
    private final BigDecimal value;

    //@ExistCurrency                                          //удалить и заменить на общую аннотацию сравнение валюты на счете и id пользователя
    @NotNull(message = "указание валюты обязателено")
    @JsonProperty(value = "currency")
    private final UUID currencyId;
}
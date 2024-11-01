package by.it_academy.jd2.service.dto;

import by.it_academy.jd2.service.validation.UniqueCurrency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CurrencyCreateDto {

    @UniqueCurrency
    @NotNull(message = "Название валюты обязателено")
    @NotBlank(message = "Название валюты не должно быть пустым")
    private final String title;

    @NotNull(message = "Описание валюты обязателено")
    @NotBlank(message = "Описание валюты не должно быть пустым")
    private final String description;
}

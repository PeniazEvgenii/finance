package by.it_academy.jd2.service.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PageDto {
    @Min(value = 0, message = "Номер страницы не может быть отрицательным")
    private final Integer page;

    @Min(value = 1, message = "Количество элементов на странице должно быть не менее 1")
    private final Integer size;
}

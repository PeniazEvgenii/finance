package by.it_academy.jd2.service.dto;

import by.it_academy.jd2.service.validation.UniqueCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationCategoryCreateDto {

    @UniqueCategory
    @NotNull(message = "Название категории обязателено")
    @NotBlank(message = "Название категории должно быть заполнено и не может быть пустым")
    private String title;

}

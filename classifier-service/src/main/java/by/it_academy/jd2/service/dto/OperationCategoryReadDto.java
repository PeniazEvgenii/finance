package by.it_academy.jd2.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class OperationCategoryReadDto {

    @JsonProperty(value = "uuid")
    private final UUID id;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private final Instant dtCreate;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private final Instant dtUpdate;

    private final String title;

}

package by.it_academy.jd2.service.dto;

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

    @JsonProperty(value = "id")
    private final UUID id;

    @JsonProperty(value = "dt_create")
    private final Instant dtCreate;

    @JsonProperty(value = "dt_update")
    private final Instant dtUpdate;

    private final String title;

}

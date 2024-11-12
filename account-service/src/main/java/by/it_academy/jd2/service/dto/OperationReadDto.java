package by.it_academy.jd2.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder
@Data
public class OperationReadDto {

    @JsonProperty(value = "uuid")
    private final UUID id;

    @JsonProperty(value = "dt_create")
    private final Instant dtCreate;

    @JsonProperty(value = "dt_update")
    private final Instant dtUpdate;

    private final Instant date;

    private final String description;

    @JsonProperty(value = "category")
    private final UUID categoryId;

    private final BigDecimal value;

    @JsonProperty(value = "currency")
    private final UUID currencyId;
}

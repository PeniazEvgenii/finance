package by.it_academy.jd2.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder
@Data
public class AccountReadDto {

    @JsonProperty(value = "uuid")
    private final UUID id;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private final Instant dtCreate;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private final Instant dtUpdate;

    private final String title;

    private final String description;

    private final BigDecimal balance;

    private final EType type;

    @JsonProperty(value = "currency")
    private final UUID currencyId;
}

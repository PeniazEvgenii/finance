package by.it_academy.jd2.commonlib.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationFeignDto {

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Instant date;

    private String description;

    private UUID categoryId;

    private BigDecimal value;

    private UUID currencyId;

    private UUID accountId;
}

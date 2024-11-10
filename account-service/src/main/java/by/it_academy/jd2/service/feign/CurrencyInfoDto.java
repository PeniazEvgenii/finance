package by.it_academy.jd2.service.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CurrencyInfoDto {

    @JsonProperty(value = "uuid")
    private final UUID id;

    private final String title;

    private final String description;
}

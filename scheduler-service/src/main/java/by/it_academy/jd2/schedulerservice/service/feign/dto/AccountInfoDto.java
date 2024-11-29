package by.it_academy.jd2.schedulerservice.service.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class AccountInfoDto {

    @JsonProperty(value = "uuid")
    private UUID accountId;

    @JsonProperty(value = "currency")
    private UUID currencyId;
}

package by.it_academy.jd2.schedulerservice.service.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OperationCategoryInfoDto {

    @JsonProperty(value = "uuid")
    private final UUID id;

    private final String title;

}

package by.it_academy.jd2.auditservice.service.dto;

import by.it_academy.jd2.commonlib.audit.EEssenceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditCreateDto {

    private UserCreateDto user;

    private String text;

    private EEssenceType type;

    @JsonProperty(value = "id")
    private String essenceId;
}

package by.it_academy.jd2.auditservice.service.dto;

import by.it_academy.jd2.commonlib.dto.EssenceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class AuditReadDto {

    @JsonProperty(value = "uuid")
    private final UUID id;

    @JsonProperty(value = "dt_create")
    private final Instant dtCreate;

    @JsonProperty(value = "dt_update")
    private final Instant dtUpdate;

    private final UserReadDto user;

    private final String text;

    private final EssenceType type;

    @JsonProperty(value = "id")
    private final String essenceId;
}

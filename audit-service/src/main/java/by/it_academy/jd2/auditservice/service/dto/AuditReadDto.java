package by.it_academy.jd2.auditservice.service.dto;

import by.it_academy.jd2.commonlib.audit.EEssenceType;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private final Instant dtCreate;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private final Instant dtUpdate;

    private final UserReadDto user;

    private final String text;

    private final EEssenceType type;

    @JsonProperty(value = "id")
    private final String essenceId;
}

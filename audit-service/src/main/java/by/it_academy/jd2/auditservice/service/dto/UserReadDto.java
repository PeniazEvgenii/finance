package by.it_academy.jd2.auditservice.service.dto;

import by.it_academy.jd2.commonlib.dto.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserReadDto {

    @JsonProperty(value = "uuid")
    private final UUID id;

    private final String mail;

    private final String fio;

    private final UserRole role;
}

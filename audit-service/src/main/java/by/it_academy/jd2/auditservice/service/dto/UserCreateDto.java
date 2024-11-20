package by.it_academy.jd2.auditservice.service.dto;

import by.it_academy.jd2.commonlib.dto.EUserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    private UUID id;

    private String fio;

    private String mail;

    private EUserRole role;

    private Instant dtUpdate;

}

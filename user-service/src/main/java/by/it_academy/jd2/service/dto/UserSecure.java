package by.it_academy.jd2.service.dto;

import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.commonlib.dto.EUserRole;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class UserSecure {

    private final UUID id;
    private final String password;
    private final Instant dtCreate;
    private final Instant dtUpdate;
    private final String mail;
    private final String fio;
    private final EUserRole role;
    private final EUserStatus status;
}

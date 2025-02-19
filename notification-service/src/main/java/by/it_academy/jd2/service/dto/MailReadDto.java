package by.it_academy.jd2.service.dto;

import by.it_academy.jd2.repository.entity.EMailStatus;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class MailReadDto {

    private final UUID id;
    private final String mail;
    private final String code;
    private final String fio;
    private final String title;
    private final EMailStatus status;
    private final Instant dtCreate;
    private final Instant dtUpdate;
}

package by.it_academy.jd2.service.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class UpdateDto {
    private final UUID accountId;

    private final UUID operationId;

    private final Instant dtUpdate;
}

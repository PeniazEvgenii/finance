package by.it_academy.jd2.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserUpdateDto {

    private final UUID id;
    private final Instant dtUpdate;
}

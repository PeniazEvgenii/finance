package by.it_academy.jd2.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class OperationUpdateDto {

    @NotNull(message = "Указание счета обязательно")
    private final UUID accountId;

    @NotNull(message = "Указание операции обязательно")
    private final UUID operationId;

    @NotNull(message = "Дата операции обязателена")
    private final Instant dtUpdate;
}

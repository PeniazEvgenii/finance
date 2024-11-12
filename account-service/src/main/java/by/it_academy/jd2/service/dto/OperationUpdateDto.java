package by.it_academy.jd2.service.dto;

import by.it_academy.jd2.service.validation.annotation.OperationUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@OperationUpdate
public class OperationUpdateDto {

    @NotNull(message = "Указание счета обязательно")
    private final UUID accountId;

    @NotNull(message = "Указание операции обязательно")
    private final UUID operationId;

    @NotNull(message = "Дата операции обязателена")
    private final Instant dtUpdate;
}

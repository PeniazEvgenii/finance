package by.it_academy.jd2.service.dto;

import by.it_academy.jd2.service.validation.annotation.AccountUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@AccountUpdate
public class AccountUpdateDto {

    @NotNull(message = "uuid счета обязателен")
    private final UUID id;

    @NotNull(message = "Время обновления обязательно")
    private final Instant dtUpdate;
}

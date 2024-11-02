package by.it_academy.jd2.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
//@UpdateAccount
public class AccountUpdateDto {

    private final UUID id;

    private final Instant dtUpdate;
//    //@UniqueAccount
//    @NotNull(message = "Название счета обязателено")
//    @NotBlank(message = "Название счета не должно быть пустым")
    private final String title;

//    @NotNull(message = "Описание счета обязателено")
//    @NotBlank(message = "Описание счета не должно быть пустым")
    private final String description;

//    @NotNull(message = "Тип счета обязателен")
    private final EType type;

//    @NotNull(message = "Валюта обязателена")  // можно в аннотации делать запрос, что такая валюта есть!!!
    private final UUID currencyId;
}

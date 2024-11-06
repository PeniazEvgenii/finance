package by.it_academy.jd2.service.dto;


import by.it_academy.jd2.commonlib.dto.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class UserReadDto {

    private final UUID uuid;

    @JsonProperty(value = "dt_create")
    // @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
   // @JsonSerialize(using = InstantToMicrosecondsSerializer.class)
    private final Instant dtCreate;

    @JsonProperty(value = "dt_update")
  //  @JsonSerialize(using = InstantToMicrosecondsSerializer.class)
   // @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private final Instant dtUpdate;

    private final String mail;

    private final String fio;

    private final UserRole role;

    private final UserStatus status;
}

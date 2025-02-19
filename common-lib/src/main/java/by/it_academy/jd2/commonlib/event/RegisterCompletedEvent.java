package by.it_academy.jd2.commonlib.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCompletedEvent {
    private UUID userId;
    private String mail;
    private String fio;
}

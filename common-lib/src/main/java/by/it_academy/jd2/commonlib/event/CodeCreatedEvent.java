package by.it_academy.jd2.commonlib.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodeCreatedEvent {
    private UUID userId;
    private String mail;
    private String code;
    private String fio;
    private String title;
}

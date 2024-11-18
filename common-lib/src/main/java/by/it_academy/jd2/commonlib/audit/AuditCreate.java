package by.it_academy.jd2.commonlib.audit;

import by.it_academy.jd2.commonlib.dto.UserToken;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditCreate {

    private UserToken user;
    private String text;
    private EEssenceType type;
    @JsonProperty(value = "id")
    private String essenceId;

}

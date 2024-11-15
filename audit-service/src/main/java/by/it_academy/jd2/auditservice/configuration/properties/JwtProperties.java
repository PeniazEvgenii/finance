package by.it_academy.jd2.auditservice.configuration.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperties {

    private String secret;

}

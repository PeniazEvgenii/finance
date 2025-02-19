package by.it_academy.jd2.configuration.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "topic.name")
public class KafkaTopicNameProperties {

    private String codeCreatedTopic;
    private String registerCompletedTopic;
}

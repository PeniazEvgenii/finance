package by.it_academy.jd2.configuration.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "async.executor")
public class AsyncExecutorProperties {

    private int corePoolSize;

    private int maxPoolSize;

    private int queueCapacity;

    private String threadNamePrefix;
}

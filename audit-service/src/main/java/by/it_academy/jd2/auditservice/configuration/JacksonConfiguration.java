package by.it_academy.jd2.auditservice.configuration;

import by.it_academy.jd2.commonlib.jackson.InstantToMicrosecondsSerializer;
import by.it_academy.jd2.commonlib.jackson.MicrosecondsToInstantConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {

    @Bean
    public MicrosecondsToInstantConverter microsecondsToInstantConverter() {
        return new MicrosecondsToInstantConverter();
    }

    @Bean
    public InstantToMicrosecondsSerializer instantToMicrosecondsSerializer() {
        return new InstantToMicrosecondsSerializer();
    }
}

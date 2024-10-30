package by.it_academy.jd2.configuration;

import by.it_academy.jd2.service.dto.jackson.InstantToMicrosecondsSerializer;
import by.it_academy.jd2.service.dto.jackson.MicrosecondsToInstantConverter;
import by.it_academy.jd2.service.dto.jackson.MicrosecondsToInstantDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {

//    @Bean
//    public MicrosecondsToInstantConverter microsecondsToInstantConverter() {
//        return new MicrosecondsToInstantConverter();
//    }
//
//    @Bean
//    public MicrosecondsToInstantDeserializer microsecondsToInstantDeserializer() {
//        return new MicrosecondsToInstantDeserializer();
//    }
//
//    @Bean
//    public InstantToMicrosecondsSerializer InstantToMicrosecondsSerializer() {
//        return new InstantToMicrosecondsSerializer();
//    }

}

package by.it_academy.jd2.service.dto.jackson;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class MicrosecondsToInstantConverter implements Converter<String, Instant> {

    @Override
    public Instant convert(String source) {
        long microseconds = Long.parseLong(source);
        long seconds = microseconds / 1_000_000;
        int nanoAdjustment = (int) (microseconds % 1_000_000) * 1_000;
        return Instant.ofEpochSecond(seconds, nanoAdjustment);
    }
}

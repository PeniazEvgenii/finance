package by.it_academy.jd2.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Instant;

@JsonComponent
public class MicrosecondsToInstantDeserializer extends JsonDeserializer<Instant> {

    @Override
    public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        long microseconds = p.getLongValue();
        return Instant.ofEpochSecond(microseconds / 1_000_000, (microseconds % 1_000_000) * 1_000);
    }
}

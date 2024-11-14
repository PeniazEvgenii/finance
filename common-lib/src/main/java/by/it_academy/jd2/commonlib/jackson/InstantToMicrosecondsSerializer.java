package by.it_academy.jd2.commonlib.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Instant;

//@JsonComponent
public class InstantToMicrosecondsSerializer extends JsonSerializer<Instant> {

    @Override
    public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        long microSec = value.getEpochSecond() * 1_000_000;
        long resultMicroSec = microSec + value.getNano() / 1000;
        gen.writeNumber(resultMicroSec);
    }
}

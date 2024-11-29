package by.it_academy.jd2.schedulerservice.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Instant startTime;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Instant stopTime;

    @Positive
    private Long interval;

    private ETimeUnit timeUnit;

}

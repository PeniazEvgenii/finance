package by.it_academy.jd2.schedulerservice.service.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduledOperationCreate {

    @Valid
    private ScheduleDto schedule;

    @Valid
    private OperationDto operation;

}

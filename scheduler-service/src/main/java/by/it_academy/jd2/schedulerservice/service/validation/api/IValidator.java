package by.it_academy.jd2.schedulerservice.service.validation.api;

import by.it_academy.jd2.schedulerservice.service.dto.OperationDto;
import by.it_academy.jd2.schedulerservice.service.dto.ScheduledOperationCreate;

import java.util.UUID;

public interface IValidator {

    void valid(UUID userId, ScheduledOperationCreate dto);
}

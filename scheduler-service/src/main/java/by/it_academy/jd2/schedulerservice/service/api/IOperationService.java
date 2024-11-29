package by.it_academy.jd2.schedulerservice.service.api;

import by.it_academy.jd2.schedulerservice.repository.entity.Operation;
import by.it_academy.jd2.schedulerservice.service.dto.OperationDto;

import java.util.UUID;

public interface IOperationService {

    Operation create(OperationDto dto);

    Operation findById(UUID id);

    Operation update(UUID id, OperationDto dto);
}

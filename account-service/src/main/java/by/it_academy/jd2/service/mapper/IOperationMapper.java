package by.it_academy.jd2.service.mapper;

import by.it_academy.jd2.repository.entity.OperationEntity;
import by.it_academy.jd2.service.dto.OperationCreateDto;
import by.it_academy.jd2.service.dto.OperationDto;
import by.it_academy.jd2.service.dto.OperationReadDto;

import java.util.UUID;

public interface IOperationMapper {

    OperationEntity mapCreate(OperationCreateDto createDto);

    OperationReadDto mapRead(OperationEntity entity);

    OperationCreateDto mapCreateDto(OperationDto dto, UUID accountId);

    OperationEntity mapUpdate(OperationDto dto, OperationEntity entity);

}

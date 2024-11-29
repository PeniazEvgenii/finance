package by.it_academy.jd2.schedulerservice.service.mapStruct;

import by.it_academy.jd2.schedulerservice.repository.entity.Operation;
import by.it_academy.jd2.schedulerservice.service.dto.OperationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OperationMapper {

    @Mapping(target = "id", ignore = true)
    Operation toEntity(OperationDto operationDto);

    OperationDto toDto(Operation operation);

    @Mapping(target = "id", ignore = true)
    Operation updateEntityFromDto(OperationDto operationDto, @MappingTarget Operation operation);
}

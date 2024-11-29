package by.it_academy.jd2.schedulerservice.service.mapStruct;

import by.it_academy.jd2.schedulerservice.repository.entity.Operation;
import by.it_academy.jd2.schedulerservice.repository.entity.Schedule;
import by.it_academy.jd2.schedulerservice.repository.entity.ScheduleOperation;
import by.it_academy.jd2.schedulerservice.service.dto.ScheduledOperationCreate;
import by.it_academy.jd2.schedulerservice.service.dto.ScheduledOperationRead;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ScheduleMapper.class, OperationMapper.class})
public interface ScheduleOperationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dtCreate", ignore = true)
    @Mapping(target = "dtUpdate", ignore = true)
    @Mapping(target = "userId", ignore = true)
    ScheduleOperation toEntity(ScheduledOperationCreate scheduledOperationCreate);


    ScheduledOperationRead toDto(ScheduleOperation scheduledOperation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dtCreate", ignore = true)
    @Mapping(target = "dtUpdate", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScheduleOperation update(ScheduledOperationCreate dto, @MappingTarget ScheduleOperation entity);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dtCreate", ignore = true)
    @Mapping(target = "dtUpdate", ignore = true)
    @Mapping(target = "userId", ignore = true)
    ScheduleOperation toEntity(Operation operation, Schedule schedule);

}

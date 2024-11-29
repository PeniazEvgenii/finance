package by.it_academy.jd2.schedulerservice.service.mapStruct;

import by.it_academy.jd2.schedulerservice.repository.entity.Schedule;
import by.it_academy.jd2.schedulerservice.service.dto.ScheduleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ScheduleMapper {

    @Mapping(target = "id", ignore = true)
    Schedule toEntity (ScheduleDto scheduleDto);

    ScheduleDto toDto (Schedule schedule);

    @Mapping(target = "id", ignore = true)
    Schedule updateEntityFromDto(ScheduleDto scheduleDto, @MappingTarget Schedule schedule);

}

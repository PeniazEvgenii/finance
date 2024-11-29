package by.it_academy.jd2.schedulerservice.service.api;

import by.it_academy.jd2.schedulerservice.repository.entity.Schedule;
import by.it_academy.jd2.schedulerservice.service.dto.ScheduleDto;

import java.util.UUID;

public interface IScheduleService {

    Schedule create(ScheduleDto dto);

    Schedule findById(UUID id);

    Schedule update(UUID id,ScheduleDto dto);

    ScheduleDto mapToDto(Schedule schedule);
}

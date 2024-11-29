package by.it_academy.jd2.schedulerservice.service;

import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.commonlib.exception.SaveException;
import by.it_academy.jd2.schedulerservice.repository.IScheduleRepository;
import by.it_academy.jd2.schedulerservice.repository.entity.Schedule;
import by.it_academy.jd2.schedulerservice.service.api.IScheduleService;
import by.it_academy.jd2.schedulerservice.service.dto.ScheduleDto;
import by.it_academy.jd2.schedulerservice.service.mapStruct.ScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService implements IScheduleService {

    private final IScheduleRepository scheduleRepository;
    private final ScheduleMapper mapper;

    @Transactional
    public Schedule create(ScheduleDto dto) {

        return Optional.of(dto)
                .map(mapper::toEntity)
                .map(scheduleRepository::saveAndFlush)
                .orElseThrow(SaveException::new);
    }

    public Schedule findById(UUID id) {
        return scheduleRepository
                .findById(id)
                .orElseThrow(IdNotFoundException::new);
    }

    @Transactional
    public Schedule update(UUID id,ScheduleDto dto) {

        Schedule schedule = this.findById(id);
        return mapper.updateEntityFromDto(dto, schedule);
    }

    @Override
    public ScheduleDto mapToDto(Schedule schedule) {
        return mapper.toDto(schedule);
    }
}

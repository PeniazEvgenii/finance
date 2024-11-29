package by.it_academy.jd2.schedulerservice.service.api;

import  by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.schedulerservice.repository.entity.ScheduleOperation;
import by.it_academy.jd2.schedulerservice.service.dto.ScheduledOperationCreate;
import by.it_academy.jd2.schedulerservice.service.dto.ScheduledOperationRead;
import jakarta.validation.Valid;

import java.time.Instant;
import java.util.UUID;

public interface IScheduleOperationService {

    void create(@Valid ScheduledOperationCreate createDto);

    PageOf<ScheduledOperationRead> findAll(PageDto pageDto);

    void update(@Valid ScheduledOperationCreate createDto, UUID id, Instant dtUpdate);

    ScheduleOperation findByIdAndUserId(UUID id, UUID userId);

    ScheduledOperationRead findDtoById(UUID id);

}

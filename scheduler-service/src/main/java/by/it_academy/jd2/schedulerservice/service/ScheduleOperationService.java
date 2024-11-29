package by.it_academy.jd2.schedulerservice.service;

import by.it_academy.jd2.commonlib.aop.LoggingAspect;
import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.commonlib.exception.UpdateTimeMismatchException;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.schedulerservice.repository.IScheduleOperationRepository;
import by.it_academy.jd2.schedulerservice.repository.entity.Operation;
import by.it_academy.jd2.schedulerservice.repository.entity.Schedule;
import by.it_academy.jd2.schedulerservice.repository.entity.ScheduleOperation;
import by.it_academy.jd2.schedulerservice.service.api.IOperationService;
import by.it_academy.jd2.schedulerservice.service.api.IScheduleOperationService;
import by.it_academy.jd2.schedulerservice.service.api.IScheduleService;
import by.it_academy.jd2.schedulerservice.service.api.IUserHolder;
import by.it_academy.jd2.schedulerservice.service.dto.ScheduledOperationCreate;
import by.it_academy.jd2.schedulerservice.service.dto.ScheduledOperationRead;
import by.it_academy.jd2.schedulerservice.service.feign.api.IAuditService;
import by.it_academy.jd2.schedulerservice.service.mapStruct.ScheduleOperationMapper;
import by.it_academy.jd2.schedulerservice.service.quartz.QuartzJobService;
import by.it_academy.jd2.schedulerservice.service.validation.api.IValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
import java.util.UUID;

@LoggingAspect
@Validated
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleOperationService implements IScheduleOperationService {

    private final IScheduleOperationRepository repository;
    private final IOperationService operationService;
    private final IScheduleService scheduleService;
    private final ScheduleOperationMapper mapper;         // MapStruct
    private final IUserHolder userHolder;
    private final IValidator validator;
    private final IAuditService auditService;

    private final QuartzJobService quartzJobService;

    @Override
    @Transactional
    public void create(@Valid ScheduledOperationCreate createDto) {
        UUID userId = userHolder.getUserId();

        validator.valid(userId, createDto);

        Operation operation = operationService.create(createDto.getOperation());
        Schedule schedule = scheduleService.create(createDto.getSchedule());

        ScheduleOperation scheduleOperation = mapper.toEntity(operation, schedule);
        scheduleOperation.setUserId(userId);

        scheduleOperation = repository.saveAndFlush(scheduleOperation);

        ScheduledOperationRead scheduledOperationRead = mapper.toDto(scheduleOperation);
        try {
            quartzJobService.scheduleJob(scheduledOperationRead);
        } catch (SchedulerException e) {
            throw new RuntimeException("error in quartzJobService", e);
        }
        auditService.send("Создана запланированная операция", scheduledOperationRead.getId());
    }

    @Override
    public PageOf<ScheduledOperationRead> findAll(PageDto pageDto) {
        UUID userId = userHolder.getUserId();

        Sort scheduleOperationSort = Sort
                .sort(ScheduleOperation.class)
                .by(ScheduleOperation::getDtCreate)
                .descending();

        PageRequest pageRequest = PageRequest.of(
                pageDto.getPage(),
                pageDto.getSize(),
                scheduleOperationSort);

        Page<ScheduledOperationRead> page = repository
                .findAllByUserId(userId, pageRequest)                  // с EntityGraph Решение N+1 проблемы
                .map(mapper::toDto);

        return PageOf.of(page);
    }

    @Override
    @Transactional
    public void update(@Valid ScheduledOperationCreate createDto, UUID id, Instant dtUpdate) {
        UUID userId = userHolder.getUserId();

        ScheduleOperation scheduleOperation = this.findByIdAndUserId(id, userId);

        validator.valid(userId, createDto);
        if (!scheduleOperation.getDtUpdate().equals(dtUpdate)) {
            throw new UpdateTimeMismatchException();
        }

        scheduleOperation = mapper.update(createDto, scheduleOperation);

        //JPA не считает изменение дочерней сущности изменением родительской. Пока что  вручную ставлю
        scheduleOperation.setDtUpdate(Instant.now());
        scheduleOperation = repository.saveAndFlush(scheduleOperation);

        try {
            quartzJobService.updateJob(mapper.toDto(scheduleOperation));
        } catch (SchedulerException e) {
            throw new RuntimeException("Error while updating job in Quartz", e);
        }

        auditService.send("Запланированная операция обновлена", id);

    }

    @Override
    public ScheduleOperation findByIdAndUserId(UUID id, UUID userId) {
        return repository.findByIdAndUserId(id, userId)
                .orElseThrow(IdNotFoundException::new);
    }

    @Override
    public ScheduledOperationRead findDtoById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(IdNotFoundException::new);
    }
}

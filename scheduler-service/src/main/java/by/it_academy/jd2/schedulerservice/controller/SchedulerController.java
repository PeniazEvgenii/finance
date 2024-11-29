package by.it_academy.jd2.schedulerservice.controller;

import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.schedulerservice.service.api.IScheduleOperationService;
import by.it_academy.jd2.schedulerservice.service.dto.ScheduledOperationCreate;
import by.it_academy.jd2.schedulerservice.service.dto.ScheduledOperationRead;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduler/operation")
public class SchedulerController {

    private final IScheduleOperationService schedulerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ScheduledOperationCreate createSchedule) {

        schedulerService.create(createSchedule);
    }

    @GetMapping
    public PageOf<ScheduledOperationRead> findAllCurrency(@RequestParam(defaultValue = "0") Integer page,
                                                          @RequestParam(defaultValue = "20") Integer size) {

        return schedulerService.findAll(new PageDto(page, size));
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public void update(@PathVariable("uuid") UUID id,
                       @PathVariable("dt_update") Instant dtUpdate,
                       @RequestBody ScheduledOperationCreate createDto) {

        schedulerService.update(createDto, id, dtUpdate);

    }
}

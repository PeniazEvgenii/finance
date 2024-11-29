package by.it_academy.jd2.schedulerservice.service.quartz;

import by.it_academy.jd2.schedulerservice.service.api.IScheduleOperationService;
import by.it_academy.jd2.schedulerservice.service.dto.OperationDto;
import by.it_academy.jd2.schedulerservice.service.feign.api.IAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OperationJob implements Job {

    private final IScheduleOperationService scheduleOperationService;
    private final IAccountService accountService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            String id = (String) jobExecutionContext.getMergedJobDataMap().get("id");
            OperationDto operation = scheduleOperationService.findDtoById(UUID.fromString(id)).getOperation();
            log.info("send on account-service operation: {}", operation);
            accountService.createOperation(operation);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}

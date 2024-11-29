package by.it_academy.jd2.schedulerservice.service.quartz;

import by.it_academy.jd2.commonlib.aop.LoggingAspect;
import by.it_academy.jd2.schedulerservice.service.dto.ScheduleDto;
import by.it_academy.jd2.schedulerservice.service.dto.ScheduledOperationRead;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.UUID;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@LoggingAspect
@Service
@RequiredArgsConstructor
public class QuartzJobService {

    private static final String WEEK_UNIT = "WEEK";

    private final Scheduler scheduler;

    public void scheduleJob(ScheduledOperationRead operation) throws SchedulerException {
        ScheduleDto schedule = operation.getSchedule();

        JobDetail jobDetail = buildJobDetail(operation);
        Trigger trigger;

        if (WEEK_UNIT.equalsIgnoreCase(schedule.getTimeUnit().name())) {
            trigger = buildTrigger(operation, jobDetail);
        } else {
            trigger = buildTriggerCron(operation, jobDetail);
        }

        scheduler.scheduleJob(jobDetail, trigger);
    }

    private Trigger buildTriggerCron(ScheduledOperationRead operation, JobDetail jobDetail) {
        ScheduleDto schedule = operation.getSchedule();
        String cronExpression = buildCron(schedule);

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(operation.getId().toString())
                .startAt(Date.from(schedule.getStartTime()))
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .endAt(Date.from(schedule.getStopTime()))
                .build();
    }

    private Trigger buildTrigger(ScheduledOperationRead operation, JobDetail jobDetail) {
        ScheduleDto schedule = operation.getSchedule();

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(operation.getId().toString())
                .startAt(Date.from(schedule.getStartTime()))
                .withSchedule(simpleSchedule()
                        .withIntervalInMilliseconds(convertToMilliseconds(
                                schedule.getInterval(), schedule.getTimeUnit().name()))
                        .repeatForever()
                )
                .endAt(Date.from(schedule.getStopTime()))
                .build();
    }

    private JobDetail buildJobDetail(ScheduledOperationRead operation) {
        return JobBuilder.newJob(OperationJob.class)
                .withIdentity(operation.getId().toString())
                .usingJobData("id", operation.getId().toString())
                .build();
    }

    public void removeJob(UUID operationId) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(operationId.toString());
        scheduler.deleteJob(jobKey);
    }

    public void updateJob(ScheduledOperationRead operation) throws SchedulerException {
        UUID id = operation.getId();

        JobKey jobKey = JobKey.jobKey(id.toString());
        TriggerKey triggerKey = TriggerKey.triggerKey(id.toString());

        if (!scheduler.checkExists(jobKey)) {
            throw new SchedulerException("Job with ID " + id + " does not exist.");
        }

        Trigger newTrigger = buildTrigger(operation, scheduler.getJobDetail(jobKey));
        scheduler.rescheduleJob(triggerKey, newTrigger);
    }

    /**
    Формат cron-выражения в Quartz состоит из 7 полей:

    Поле	Значение	Пример
    Секунды	0–59	0
    Минуты	0–59	30
    Часы	0–23	14
    День месяца	1–31	15
    Месяц	1–12 или JAN–DEC	6
    День недели	1–7 (1 = Воскресенье)	2
    Год (опцион.)	1970–2099	2023

Символы для выражений:
* — любое значение.
? — используется в полях "День месяца" или "День недели", если значение не указано.
- — диапазон (например, 1-5 означает с Понедельника по Пятницу).
, — список значений (например, 1,3,5).
   */

    private String buildCron(ScheduleDto schedule) {
        Long interval = schedule.getInterval();

        String unit = schedule.getTimeUnit().name().toUpperCase();

        return switch (unit) {
            case "SECOND" -> "*/" + interval + " * * * * ?";
            case "MINUTE" -> "0 */" + interval + " * * * ?";
            case "HOUR" -> "0 0 */" + interval + " * * ?";
            case "DAY" -> "0 0 0 */" + interval + " * ?";
            case "MONTH" -> "0 0 0 1 */" + interval + " ?";
            case "YEAR" -> "0 0 0 1 1 ? */" + interval;
            default -> throw new IllegalArgumentException("Invalid time unit: " + unit);
        };

    }

    //все удалить и оставить только конвертер в WEEK!!!
    private long convertToMilliseconds(long interval, String timeUnit) {
        return switch (timeUnit.toUpperCase()) {
            case "SECOND" -> interval * 1000;
            case "MINUTE" -> interval * 60 * 1000;
            case "HOUR" -> interval * 60 * 60 * 1000;
            case "DAY" -> interval * 24 * 60 * 60 * 1000;
            case "WEEK" -> interval * 7 * 24 * 60 * 60 * 1000;
            case "MONTH" -> interval * 30 * 24 * 60 * 60 * 1000;
            case "YEAR" -> interval * 365 * 24 * 60 * 60 * 1000;
            default -> throw new IllegalArgumentException("Invalid time unit: " + timeUnit);
        };
    }
}

package by.it_academy.jd2.schedulerservice.service.validation;

import by.it_academy.jd2.commonlib.exception.CurrencyMismatchException;
import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.schedulerservice.service.dto.OperationDto;
import by.it_academy.jd2.schedulerservice.service.dto.ScheduleDto;
import by.it_academy.jd2.schedulerservice.service.dto.ScheduledOperationCreate;
import by.it_academy.jd2.schedulerservice.service.feign.api.IAccountService;
import by.it_academy.jd2.schedulerservice.service.feign.dto.AccountInfoDto;
import by.it_academy.jd2.schedulerservice.service.validation.api.IValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Validator implements IValidator {

    private final IAccountService accountService;

    public void valid(UUID userId, ScheduledOperationCreate dto) {

        OperationDto operationDto = dto.getOperation();
        ScheduleDto scheduleDto = dto.getSchedule();

        AccountInfoDto accountInfoDto = accountService
                .getAccountInfo(operationDto.getAccountId(), userId)
                .orElseThrow(IdNotFoundException::new);


        if (!accountInfoDto.getCurrencyId().equals(operationDto.getCurrencyId())) {
            throw new CurrencyMismatchException();
        }

        if (scheduleDto.getStopTime().isBefore(scheduleDto.getStartTime())) {
            throw new IllegalArgumentException("Start time must be before stop time");
        }
    }

}

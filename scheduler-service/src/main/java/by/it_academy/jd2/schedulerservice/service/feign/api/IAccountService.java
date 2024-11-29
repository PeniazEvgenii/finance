package by.it_academy.jd2.schedulerservice.service.feign.api;

import by.it_academy.jd2.schedulerservice.service.dto.OperationDto;
import by.it_academy.jd2.schedulerservice.service.feign.dto.AccountInfoDto;

import java.util.Optional;
import java.util.UUID;

public interface IAccountService {

    void createOperation(OperationDto operationDto);

    Optional<AccountInfoDto> getAccountInfo(UUID accountId, UUID userId);
}

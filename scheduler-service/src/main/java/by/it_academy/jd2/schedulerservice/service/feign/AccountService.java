package by.it_academy.jd2.schedulerservice.service.feign;

import by.it_academy.jd2.commonlib.dto.OperationFeignDto;
import by.it_academy.jd2.commonlib.exception.AuditSaveException;
import by.it_academy.jd2.schedulerservice.service.dto.OperationDto;
import by.it_academy.jd2.schedulerservice.service.feign.api.IAccountService;
import by.it_academy.jd2.schedulerservice.service.feign.api.IAuditService;
import by.it_academy.jd2.schedulerservice.service.feign.client.IAccountClient;
import by.it_academy.jd2.schedulerservice.service.feign.dto.AccountInfoDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static by.it_academy.jd2.commonlib.constant.Actions.AUDIT_OPERATION_SHED_CREATE;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final IAccountClient accountClient;
    private final IAuditService auditService;

    public Optional<AccountInfoDto> getAccountInfo(UUID accountId, UUID userId) {

        ResponseEntity<AccountInfoDto> account = accountClient.findAccountByIdAndUserId(accountId, userId);
        return Optional.ofNullable(account.getBody());
    }


    public void createOperation(OperationDto operationDto) {
        OperationFeignDto feignDto = OperationFeignDto.builder()
                .accountId(operationDto.getAccountId())
                .categoryId(operationDto.getCategoryId())
                .currencyId(operationDto.getCurrencyId())
                .description(operationDto.getDescription())
                .value(operationDto.getValue())
                .date(Instant.now())
                .build();


        ResponseEntity<UUID> operation = accountClient.createOperation(feignDto);
        UUID operationId = operation.getBody();
        if (operationId == null) {
            throw new AuditSaveException();
        }
    }
}

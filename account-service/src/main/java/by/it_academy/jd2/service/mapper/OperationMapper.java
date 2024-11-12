package by.it_academy.jd2.service.mapper;

import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.repository.entity.AccountEntity;
import by.it_academy.jd2.repository.entity.OperationEntity;
import by.it_academy.jd2.service.api.IAccountService;
import by.it_academy.jd2.service.dto.OperationCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IOperationMapper {

    private final IAccountService accountService;

    public OperationEntity mapCreate(OperationCreateDto createDto, UUID accountId) {
        AccountEntity accountEntity = getAccount(accountId);


return null;

    }

    private AccountEntity getAccount(UUID accountId) {
        return accountService
                .findEntityById(accountId)
                .orElseThrow(IdNotFoundException::new);
    }
}

package by.it_academy.jd2.service.mapper;

import by.it_academy.jd2.commonlib.exception.CurrencyMismatchException;
import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.repository.entity.AccountEntity;
import by.it_academy.jd2.repository.entity.OperationEntity;
import by.it_academy.jd2.service.api.IAccountService;
import by.it_academy.jd2.service.dto.OperationCreateDto;
import by.it_academy.jd2.service.dto.OperationDto;
import by.it_academy.jd2.service.dto.OperationReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OperationMapper implements IOperationMapper {

    private final IAccountService accountService;

    @Override
    public OperationEntity mapCreate(OperationCreateDto createDto) {
        AccountEntity account = accountService
                .findEntityById(createDto.getAccountId())
                .orElseThrow(IdNotFoundException::new);
        if(!account.getCurrencyId().equals(createDto.getCurrencyId())) {
            throw new CurrencyMismatchException();
        }

        account.addValue(createDto.getValue());

        return OperationEntity.builder()
                .date(createDto.getDate())
                .description(createDto.getDescription())
                .categoryId(createDto.getCategoryId())
                .value(createDto.getValue())
                .accountEntity(account)
                .build();
    }

    public OperationReadDto mapRead(OperationEntity entity) {
       return OperationReadDto.builder()
               .id(entity.getId())
               .dtCreate(entity.getDtCreate())
               .dtUpdate(entity.getDtUpdate())
               .date(entity.getDate())
               .description(entity.getDescription())
               .categoryId(entity.getCategoryId())
               .value(entity.getValue())
               .currencyId(entity.getAccountEntity().getCurrencyId())
               .build();
    }

    public OperationEntity mapUpdate(OperationDto dto, OperationEntity entity) {
        AccountEntity accountEntity = entity.getAccountEntity();
        if(!checkCurrency(dto, accountEntity)) {
            throw new CurrencyMismatchException();
        }
        BigDecimal oldBalance = accountEntity.getBalance();
        BigDecimal newBalance = oldBalance.subtract(entity.getValue()).add(dto.getValue());
        accountEntity.setBalance(newBalance);

        entity.setDate(dto.getDate());
        entity.setDescription(dto.getDescription());
        entity.setCategoryId(dto.getCategoryId());
        entity.setValue(dto.getValue());
        return entity;
    }

    public OperationCreateDto mapCreateDto(OperationDto dto, UUID accountId) {
        return OperationCreateDto.builder()
                .date(dto.getDate())
                .description(dto.getDescription())
                .categoryId(dto.getCategoryId())
                .value(dto.getValue())
                .currencyId(dto.getCurrencyId())
                .accountId(accountId)
                .build();
    }

    private boolean checkCurrency(OperationDto dto, AccountEntity entity) {
        return entity.getCurrencyId().equals(dto.getCurrencyId());
    }
}

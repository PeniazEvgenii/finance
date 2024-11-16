package by.it_academy.jd2.service.mapper;

import by.it_academy.jd2.repository.entity.AccountEntity;
import by.it_academy.jd2.service.api.IUserHolderService;
import by.it_academy.jd2.service.dto.AccountCreateDto;
import by.it_academy.jd2.service.dto.AccountReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;


@Component
@RequiredArgsConstructor
public class AccountMapper implements IAccountMapper {

    private static final Integer START_BALANCE = 0;

    private final IUserHolderService userHolderService;

    @Override
    public AccountEntity mapCreate(AccountCreateDto createDto) {
        return AccountEntity.builder()
                .title(createDto.getTitle())
                .description(createDto.getDescription())
                .balance(new BigDecimal(START_BALANCE))
                .type(createDto.getType())
                .currencyId(createDto.getCurrencyId())
                .userId(userHolderService.getUserId())
                .build();
    }

    @Override
    public AccountReadDto mapRead(AccountEntity entity) {
        return AccountReadDto.builder()
                .id(entity.getId())
                .dtCreate(entity.getDtCreate())
                .dtUpdate(entity.getDtUpdate())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .balance(entity.getBalance())
                .type(entity.getType())
                .currencyId(entity.getCurrencyId())
                .build();
    }

    @Override
    public AccountEntity mapUpdate(AccountCreateDto createDto, AccountEntity entity) {
        entity.setTitle(createDto.getTitle());
        entity.setDescription(createDto.getDescription());
        entity.setType(createDto.getType());
        entity.setCurrencyId(createDto.getCurrencyId());
        return entity;
    }


}

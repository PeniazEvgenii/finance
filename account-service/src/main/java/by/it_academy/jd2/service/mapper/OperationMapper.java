package by.it_academy.jd2.service.mapper;

import by.it_academy.jd2.repository.entity.AccountEntity;
import by.it_academy.jd2.repository.entity.OperationEntity;
import by.it_academy.jd2.service.dto.OperationCreateDto;
import by.it_academy.jd2.service.dto.OperationReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class OperationMapper implements IOperationMapper {

    @Override
    public OperationEntity mapCreate(OperationCreateDto createDto, AccountEntity account) {

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

    public OperationEntity mapUpdate(OperationCreateDto dto, OperationEntity entity) {
        AccountEntity accountEntity = entity.getAccountEntity();

        BigDecimal oldBalance = accountEntity.getBalance();
        BigDecimal newBalance = oldBalance.subtract(entity.getValue()).add(dto.getValue());
        accountEntity.setBalance(newBalance);

        entity.setDate(dto.getDate());
        entity.setDescription(dto.getDescription());
        entity.setCategoryId(dto.getCategoryId());
        entity.setValue(dto.getValue());
        return entity;
    }

}

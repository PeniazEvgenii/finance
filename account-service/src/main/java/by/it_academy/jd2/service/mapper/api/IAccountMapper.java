package by.it_academy.jd2.service.mapper.api;

import by.it_academy.jd2.repository.entity.AccountEntity;
import by.it_academy.jd2.service.dto.AccountCreateDto;
import by.it_academy.jd2.service.dto.AccountReadDto;

public interface IAccountMapper {

    AccountEntity mapCreate(AccountCreateDto createDto);

    AccountReadDto mapRead(AccountEntity entity);

    AccountEntity mapUpdate(AccountCreateDto createDto, AccountEntity entity);

}

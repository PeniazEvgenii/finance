package by.it_academy.jd2.service.mapper;

import by.it_academy.jd2.repository.entity.AccountEntity;
import by.it_academy.jd2.service.dto.AccountCreateDto;
import by.it_academy.jd2.service.dto.AccountReadDto;
import by.it_academy.jd2.service.dto.AccountUpdateDto;

import java.time.Instant;
import java.util.UUID;

public interface IAccountMapper {

    AccountEntity mapCreate(AccountCreateDto createDto);

    AccountReadDto mapRead(AccountEntity entity);

    AccountEntity mapUpdate(AccountUpdateDto updateDto, AccountEntity entity);

    AccountUpdateDto mapUpdateDto(AccountCreateDto createDto,
                                  UUID accountId, Instant dtUpdate);
}

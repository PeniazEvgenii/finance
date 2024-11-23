package by.it_academy.jd2.service.mapper.api;

import by.it_academy.jd2.repository.entity.AccountEntity;
import by.it_academy.jd2.repository.entity.OperationEntity;
import by.it_academy.jd2.service.dto.OperationCreateDto;
import by.it_academy.jd2.service.dto.OperationReadDto;

public interface IOperationMapper {

    OperationEntity mapCreate(OperationCreateDto createDto, AccountEntity account);

    OperationReadDto mapRead(OperationEntity entity);

    OperationEntity mapUpdate(OperationCreateDto dto, OperationEntity entity);

}

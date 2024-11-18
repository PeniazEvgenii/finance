package by.it_academy.jd2.auditservice.service.mapper.api;

import by.it_academy.jd2.auditservice.repository.entity.UserEntity;
import by.it_academy.jd2.auditservice.service.dto.UserCreateDto;
import by.it_academy.jd2.auditservice.service.dto.UserReadDto;
import by.it_academy.jd2.commonlib.dto.UserToken;

public interface IUserMapper {

    UserReadDto mapRead(UserEntity entity);

    UserEntity mapCreate(UserCreateDto userCreate);
}

package by.it_academy.jd2.auditservice.service.mapper.api;

import by.it_academy.jd2.auditservice.repository.entity.UserEntity;
import by.it_academy.jd2.auditservice.service.dto.UserReadDto;

public interface IUserMapper {

    UserReadDto mapRead(UserEntity entity);
}

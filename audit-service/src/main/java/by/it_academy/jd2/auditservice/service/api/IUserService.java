package by.it_academy.jd2.auditservice.service.api;

import by.it_academy.jd2.auditservice.repository.entity.UserEntity;
import by.it_academy.jd2.auditservice.service.dto.UserCreateDto;

import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    Optional<UserEntity> findById(UUID id);

    UserEntity create(UserEntity user);

    UserEntity createOrUpdateIfNeeded(UserCreateDto userCreate);
}

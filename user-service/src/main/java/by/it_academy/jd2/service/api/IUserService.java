package by.it_academy.jd2.service.api;

import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.service.dto.*;
import by.it_academy.jd2.service.validation.group.CreateAction;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    PageOf<UserReadDto> findAll(@Valid PageDto pageDto);

    UserReadDto findById(UUID id);

    void create(UserCreateDto userCreateDto);

    public void update(UserCreateDto createDto, UserUpdateDto userUpdateDt);

    Optional<UserReadDto> findByMail(String mail);

    List<UserEntity> findByStatusWithoutCode(EUserStatus status);

    Optional<UserSecure> findByMailWithPass(String mail);
}

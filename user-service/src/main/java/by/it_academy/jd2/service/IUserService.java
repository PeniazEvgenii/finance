package by.it_academy.jd2.service;

import by.it_academy.jd2.page.PageOf;
import by.it_academy.jd2.service.dto.*;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    PageOf<UserReadDto> findAll(@Valid PageDto pageDto);

    Optional<UserReadDto> findById(UUID id);

    void create(@Valid UserCreateDto userCreateDto);

    public void update(@Valid UserUpdateDto userUpdateDto);

    Optional<UserReadDto> findByMail(String mail);
}

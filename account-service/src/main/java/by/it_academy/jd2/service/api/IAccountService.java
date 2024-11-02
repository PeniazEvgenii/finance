package by.it_academy.jd2.service.api;

import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.service.dto.AccountCreateDto;
import by.it_academy.jd2.service.dto.AccountReadDto;
import by.it_academy.jd2.service.dto.AccountUpdateDto;
import by.it_academy.jd2.service.dto.PageDto;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.UUID;

public interface IAccountService {

    void create(@Valid AccountCreateDto createDto);

    PageOf<AccountReadDto> findAll(@Valid PageDto pageDto);

    Optional<AccountReadDto> findById(UUID id);

    void update(@Valid AccountUpdateDto updateDto);
}

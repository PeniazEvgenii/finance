package by.it_academy.jd2.service.api;

import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.service.dto.CurrencyCreateDto;
import by.it_academy.jd2.service.dto.CurrencyReadDto;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.UUID;

public interface ICurrencyService {

    PageOf<CurrencyReadDto> findAll(@Valid PageDto pageDto);

    void create(@Valid CurrencyCreateDto createDto);

    Optional<CurrencyReadDto> findByTitle(String title);

    Optional<CurrencyReadDto> findById(UUID id);

}

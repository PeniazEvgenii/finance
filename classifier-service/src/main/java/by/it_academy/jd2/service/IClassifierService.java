package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.service.dto.*;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.UUID;

public interface IClassifierService {
    PageOf<CurrencyReadDto> findAllCurrency(@Valid PageDto pageDto);

    void createCurrency(CurrencyCreateDto createDto);

    PageOf<OperationCategoryReadDto> findAllOperationCategory(@Valid PageDto pageDto);

    void createOperationCategory(OperationCategoryCreateDto createDto);

    Optional<CurrencyReadDto> findCurrencyByTitle(String title);

    Optional<OperationCategoryReadDto> findOperationCategoryByTitle(String title);

    Optional<CurrencyReadDto> findCurrencyById(UUID id);

    Optional<OperationCategoryReadDto> findCategoryById(UUID id);
}

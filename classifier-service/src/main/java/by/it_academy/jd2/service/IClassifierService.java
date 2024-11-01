package by.it_academy.jd2.service;

import by.it_academy.jd2.page.PageOf;
import by.it_academy.jd2.service.dto.*;
import jakarta.validation.Valid;

import java.util.Optional;

public interface IClassifierService {
    PageOf<CurrencyReadDto> findAllCurrency(@Valid PageDto pageDto);

    void createCurrency(CurrencyCreateDto createDto);

    PageOf<OperationCategoryReadDto> findAllOperationCategory(@Valid PageDto pageDto);

    void createOperationCategory(OperationCategoryCreateDto createDto);

    Optional<CurrencyReadDto> findCurrencyByTitle(String title);

    Optional<OperationCategoryReadDto> findOperationCategoryByTitle(String title);
}

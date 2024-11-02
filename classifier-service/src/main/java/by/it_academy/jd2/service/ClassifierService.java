package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.repository.ICurrencyRepository;
import by.it_academy.jd2.repository.IOperationCategoryRepository;
import by.it_academy.jd2.service.dto.*;
import by.it_academy.jd2.service.mapper.api.ICurrencyMapper;
import by.it_academy.jd2.service.mapper.api.IOperationCategoryMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;


@Validated
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ClassifierService implements IClassifierService {

    private final ICurrencyRepository currencyRepository;
    private final IOperationCategoryRepository operationCategoryRepository;
    private final ICurrencyMapper currencyMapper;
    private final IOperationCategoryMapper operationCategoryMapper;

    public PageOf<CurrencyReadDto> findAllCurrency(@Valid PageDto pageDto) {
        Sort.TypedSort<String> sortCurrency = Sort
                .sort(CurrencyReadDto.class)
                .by(CurrencyReadDto::getTitle);

        PageRequest pageRequest = PageRequest.of(
                pageDto.getPage(),
                pageDto.getSize(),
                sortCurrency);

        Page<CurrencyReadDto> pageCurrency = currencyRepository
                .findAll(pageRequest)
                .map(currencyMapper::mapRead);

        return PageOf.of(pageCurrency);
    }

    @Transactional
    public void createCurrency(CurrencyCreateDto createDto) {
        Optional.of(createDto)
                .map(currencyMapper::mapCreate)
                .map(currencyRepository::saveAndFlush)
                .orElseThrow();                                                                                          //может свое исключение
    }

    public PageOf<OperationCategoryReadDto> findAllOperationCategory(@Valid PageDto pageDto) {
        Sort.TypedSort<String> sortCategory = Sort.
                sort(OperationCategoryReadDto.class)
                .by(OperationCategoryReadDto::getTitle);

        PageRequest pageRequest = PageRequest.of(
                pageDto.getPage(),
                pageDto.getSize(),
                sortCategory);

        Page<OperationCategoryReadDto> pageCategory = operationCategoryRepository
                .findAll(pageRequest)
                .map(operationCategoryMapper::mapRead);

        return PageOf.of(pageCategory);
    }

    @Transactional
    public void createOperationCategory(OperationCategoryCreateDto createDto) {
        Optional.of(createDto)
                .map(operationCategoryMapper::mapCreate)
                .map(operationCategoryRepository::saveAndFlush)
                .orElseThrow();                                                                                           //может свое исключение
    }

    public Optional<CurrencyReadDto> findCurrencyByTitle(String title) {
        return currencyRepository.findByTitle(title)
                .map(currencyMapper::mapRead);
    }

    public Optional<OperationCategoryReadDto> findOperationCategoryByTitle(String title) {
        return operationCategoryRepository.findByTitle(title)
                .map(operationCategoryMapper::mapRead);
    }

    @Override
    public Optional<CurrencyReadDto> findCurrencyById(UUID id) {
        return currencyRepository.findById(id)
                .map(currencyMapper::mapRead);
    }

    @Override
    public Optional<OperationCategoryReadDto> findCategoryById(UUID id) {
        return operationCategoryRepository.findById(id)
                .map(operationCategoryMapper::mapRead);
    }


}

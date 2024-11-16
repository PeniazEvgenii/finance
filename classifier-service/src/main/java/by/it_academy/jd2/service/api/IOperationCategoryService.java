package by.it_academy.jd2.service.api;

import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.service.dto.OperationCategoryCreateDto;
import by.it_academy.jd2.service.dto.OperationCategoryReadDto;
import by.it_academy.jd2.service.dto.PageDto;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.UUID;

public interface IOperationCategoryService {

    PageOf<OperationCategoryReadDto> findAll(@Valid PageDto pageDto);

    void create(@Valid OperationCategoryCreateDto createDto);

    Optional<OperationCategoryReadDto> findByTitle(String title);

    Optional<OperationCategoryReadDto> findById(UUID id);

}

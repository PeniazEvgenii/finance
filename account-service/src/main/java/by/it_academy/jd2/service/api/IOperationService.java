package by.it_academy.jd2.service.api;

import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.service.dto.*;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.UUID;

public interface IOperationService {

    void create(@Valid OperationCreateDto createDto);

    PageOf<OperationReadDto> findAll(@Valid PageDto pageDto, UUID accountId);

    Optional<OperationReadDto> findByIdAndAccountId(UUID id, UUID accountId);

    void update(@Valid OperationDto dto, @Valid OperationUpdateDto updateDto);

    void delete(@Valid OperationUpdateDto updateDto);
}

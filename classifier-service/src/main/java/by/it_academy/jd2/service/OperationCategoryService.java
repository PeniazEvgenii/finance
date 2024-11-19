package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.exception.SaveException;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.repository.IOperationCategoryRepository;
import by.it_academy.jd2.repository.entity.OperationCategoryEntity;
import by.it_academy.jd2.service.api.IOperationCategoryService;
import by.it_academy.jd2.service.dto.OperationCategoryCreateDto;
import by.it_academy.jd2.service.dto.OperationCategoryReadDto;
import by.it_academy.jd2.service.dto.PageDto;
import by.it_academy.jd2.service.feign.api.IAuditService;
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
public class OperationCategoryService implements IOperationCategoryService {

    private final static String CREATE_CATEGORY = "Создана категория операции";

    private final IOperationCategoryRepository operationCategoryRepository;
    private final IOperationCategoryMapper operationCategoryMapper;
    private final IAuditService auditService;

    @Transactional
    @Override
    public void create(@Valid OperationCategoryCreateDto createDto) {
        Optional.of(createDto)
                .map(operationCategoryMapper::mapCreate)
                .map(operationCategoryRepository::saveAndFlush)
                .ifPresentOrElse(
                        entity -> auditService.send(CREATE_CATEGORY, entity.getId()),
                        SaveException::new);
    }

    @Override
    public PageOf<OperationCategoryReadDto> findAll(@Valid PageDto pageDto) {
        Sort sortCategory = Sort.sort(OperationCategoryEntity.class)
                .by(OperationCategoryEntity::getTitle)
                .ascending();

        PageRequest pageRequest = PageRequest.of(
                pageDto.getPage(),
                pageDto.getSize(),
                sortCategory);

        Page<OperationCategoryReadDto> pageCategory = operationCategoryRepository
                .findAll(pageRequest)
                .map(operationCategoryMapper::mapRead);

        return PageOf.of(pageCategory);
    }

    @Override
    public Optional<OperationCategoryReadDto> findByTitle(String title) {
        return operationCategoryRepository.findByTitleIgnoreCase(title)
                .map(operationCategoryMapper::mapRead);
    }

    @Override
    public Optional<OperationCategoryReadDto> findById(UUID id) {
        return operationCategoryRepository.findById(id)
                .map(operationCategoryMapper::mapRead);
    }
}

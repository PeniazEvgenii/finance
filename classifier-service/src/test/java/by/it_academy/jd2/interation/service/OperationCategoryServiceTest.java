package by.it_academy.jd2.interation.service;

import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.interation.IntegrationTestBase;
import by.it_academy.jd2.repository.IOperationCategoryRepository;
import by.it_academy.jd2.service.api.IOperationCategoryService;
import by.it_academy.jd2.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class OperationCategoryServiceTest extends IntegrationTestBase {

    private final IOperationCategoryService operationCategoryService;
    private final IOperationCategoryRepository operationCategoryRepository;

    @Test
    void create() {
        OperationCategoryCreateDto car = new OperationCategoryCreateDto("авто");
        operationCategoryService.create(car);
        operationCategoryRepository.findByTitleIgnoreCase("авто").
                ifPresent(category -> assertEquals(car.getTitle(), category.getTitle()));
    }

    @Test
    void findAll() {
        PageOf<OperationCategoryReadDto> all = operationCategoryService.findAll(new PageDto(0, 3));
        assertThat(all.getContent()).hasSize(3);
        assertEquals(3, all.getSize());
        assertEquals(0, all.getNumber());
        assertTrue(all.isFirst());
    }

    @Test
    void findByTitle() {
        Optional<OperationCategoryReadDto> title = operationCategoryService.findByTitle("тачка");
        Optional<OperationCategoryReadDto> title2 = operationCategoryService.findByTitle("ТаЧкА");
        assertTrue(title.isPresent());
        assertTrue(title2.isPresent());
        assertEquals(title.get().getId(), title2.get().getId());
    }

    @Test
    void findById() {
        Optional<OperationCategoryReadDto> byId = operationCategoryService.findById(UUID.fromString("cecfc4ec-9c99-4afe-a836-2e499a2e0561"));
        assertTrue(byId.isPresent());
        byId.ifPresent(category -> assertEquals("тачка", category.getTitle()));
    }
}
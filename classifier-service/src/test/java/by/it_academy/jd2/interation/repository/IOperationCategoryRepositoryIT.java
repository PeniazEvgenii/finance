package by.it_academy.jd2.interation.repository;

import by.it_academy.jd2.interation.IntegrationTestBase;
import by.it_academy.jd2.interation.annotation.IT;
import by.it_academy.jd2.repository.IOperationCategoryRepository;
import by.it_academy.jd2.repository.entity.OperationCategoryEntity;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class IOperationCategoryRepositoryIT extends IntegrationTestBase {

    private final IOperationCategoryRepository repository;

    @Test
    void findByTitleIgnoreCase() {
        Optional<OperationCategoryEntity> operationCategory = repository.findByTitleIgnoreCase("тачка");
        assertTrue(operationCategory.isPresent());
        operationCategory.ifPresent(category -> assertEquals(UUID.fromString("cecfc4ec-9c99-4afe-a836-2e499a2e0561"), category.getId()));
    }
}
package by.it_academy.jd2.repository;

import by.it_academy.jd2.repository.entity.OperationCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IOperationCategoryRepository extends JpaRepository<OperationCategoryEntity, UUID> {

    Optional<OperationCategoryEntity> findByTitle(String title);
}

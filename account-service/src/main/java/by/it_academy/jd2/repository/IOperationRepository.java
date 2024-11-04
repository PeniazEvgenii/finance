package by.it_academy.jd2.repository;

import by.it_academy.jd2.repository.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IOperationRepository extends JpaRepository<OperationEntity, UUID> {
}

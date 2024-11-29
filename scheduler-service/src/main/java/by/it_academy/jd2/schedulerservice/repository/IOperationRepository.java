package by.it_academy.jd2.schedulerservice.repository;

import by.it_academy.jd2.schedulerservice.repository.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IOperationRepository extends JpaRepository<Operation, UUID> {
}

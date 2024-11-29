package by.it_academy.jd2.schedulerservice.repository;

import by.it_academy.jd2.schedulerservice.repository.entity.ScheduleOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IScheduleOperationRepository extends JpaRepository<ScheduleOperation, UUID> {

    @EntityGraph(attributePaths = {"operation", "schedule"})
    Page<ScheduleOperation> findAllByUserId(UUID userId, Pageable pageable);

    @EntityGraph(attributePaths = {"operation", "schedule"})
    Optional<ScheduleOperation> findByIdAndUserId(UUID id, UUID userId);

}

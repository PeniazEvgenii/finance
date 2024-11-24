package by.it_academy.jd2.auditservice.repository;

import by.it_academy.jd2.auditservice.repository.entity.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAuditRepository extends JpaRepository<AuditEntity, UUID> {

    @EntityGraph(attributePaths = "user")
    Page<AuditEntity> findAllBy(Pageable pageable);         //decide проблем N + 1
}

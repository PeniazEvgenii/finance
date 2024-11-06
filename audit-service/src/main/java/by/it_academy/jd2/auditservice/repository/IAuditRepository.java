package by.it_academy.jd2.auditservice.repository;

import by.it_academy.jd2.auditservice.repository.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAuditRepository extends JpaRepository<AuditEntity, UUID> {

}

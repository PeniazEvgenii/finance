package by.it_academy.jd2.repository;

import by.it_academy.jd2.repository.entity.OperationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface IOperationRepository extends JpaRepository<OperationEntity, UUID> {

    @Query("select o from OperationEntity o where o.accountEntity.id = :accountId")
    Page<OperationEntity> findAllByAccountId(UUID accountId, Pageable pageable);

    @Query("select o from OperationEntity o where o.accountEntity.id = :accountId and o.id = :id")
    Optional<OperationEntity> findByIdAndAccountId(UUID id, UUID accountId);

}

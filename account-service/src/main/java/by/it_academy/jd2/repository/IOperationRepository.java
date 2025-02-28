package by.it_academy.jd2.repository;

import by.it_academy.jd2.repository.entity.OperationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface IOperationRepository extends JpaRepository<OperationEntity, UUID> {

    @Query("select o from OperationEntity o join fetch o.accountEntity ae where ae.id = :accountId and ae.userId = :userId")
    Page<OperationEntity> findAllByAccountIdAndUserId(UUID accountId, UUID userId, Pageable pageable);

    @Query("select o from OperationEntity o join fetch o.accountEntity ae where o.id = :id and ae.userId = :userId")
    Optional<OperationEntity> findByIdAndUserId(UUID id, UUID userId);

}

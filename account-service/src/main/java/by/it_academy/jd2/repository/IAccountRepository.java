package by.it_academy.jd2.repository;

import by.it_academy.jd2.repository.entity.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IAccountRepository extends JpaRepository<AccountEntity, UUID> {

    Page<AccountEntity> findAllByUserId(UUID userId, Pageable pageable);

    Optional<AccountEntity> findByIdAndUserId(UUID id, UUID userId);


}

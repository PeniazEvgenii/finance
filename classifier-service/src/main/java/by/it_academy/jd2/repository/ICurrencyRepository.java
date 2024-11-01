package by.it_academy.jd2.repository;

import by.it_academy.jd2.repository.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ICurrencyRepository extends JpaRepository<CurrencyEntity, UUID> {

    Optional<CurrencyEntity> findByTitle(String title);
}

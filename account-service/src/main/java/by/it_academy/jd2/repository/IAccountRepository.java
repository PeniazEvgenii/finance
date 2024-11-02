package by.it_academy.jd2.repository;

import by.it_academy.jd2.repository.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAccountRepository extends JpaRepository<AccountEntity, UUID> {


}

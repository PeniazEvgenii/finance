package by.it_academy.jd2.repository;

import by.it_academy.jd2.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserEntity, UUID>,
                                            QuerydslPredicateExecutor<UserEntity> {

    Optional<UserEntity> findByMail(String mail);
}

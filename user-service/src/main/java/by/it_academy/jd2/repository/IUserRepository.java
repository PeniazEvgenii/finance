package by.it_academy.jd2.repository;

import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.dto.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserEntity, UUID>,
                                            QuerydslPredicateExecutor<UserEntity> {

    Optional<UserEntity> findByMailIgnoreCase(String mail);

    @Query("select u FROM UserEntity u LEFT JOIN CodeEntity c on u.id = c.user.id where u.status = :userStatus and c.user.id is NULL")
    List<UserEntity> findByStatusWithoutCode(UserStatus userStatus);
}

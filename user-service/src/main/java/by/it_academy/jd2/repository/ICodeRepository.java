package by.it_academy.jd2.repository;

import by.it_academy.jd2.repository.entity.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ICodeRepository extends JpaRepository<CodeEntity, UUID> {

    @Query("select c from CodeEntity c join fetch c.user u where LOWER(u.mail) = LOWER(:mail)")
    Optional<CodeEntity> findByMail(String mail);
}

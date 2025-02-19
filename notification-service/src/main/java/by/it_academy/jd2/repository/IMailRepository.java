package by.it_academy.jd2.repository;

import by.it_academy.jd2.repository.entity.EMailStatus;
import by.it_academy.jd2.repository.entity.MailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IMailRepository extends JpaRepository<MailEntity, UUID> {

    List<MailEntity> findByStatus(EMailStatus status);
}

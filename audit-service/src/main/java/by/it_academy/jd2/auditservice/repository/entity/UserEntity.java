package by.it_academy.jd2.auditservice.repository.entity;

import by.it_academy.jd2.commonlib.dto.EUserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class UserEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String fio;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EUserRole role;

    @CreatedDate
    @Column(name = "dt_create")
    private Instant dtCreate;

    @LastModifiedDate
    @Column(name = "dt_update")
    private Instant dtUpdate;
}

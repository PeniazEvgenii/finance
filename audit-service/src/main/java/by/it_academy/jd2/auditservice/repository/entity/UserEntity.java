package by.it_academy.jd2.auditservice.repository.entity;


import by.it_academy.jd2.commonlib.dto.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(EntityListeners.class)
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
    private UserRole role;

    @CreatedDate
    @Column(name = "dt_create", nullable = false)
    private Instant dtCreate;

    @LastModifiedDate
    @Column(name = "dt_update", nullable = false)
    private Instant dtUpdate;
}

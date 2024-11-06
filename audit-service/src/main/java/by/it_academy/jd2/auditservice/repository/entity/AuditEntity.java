package by.it_academy.jd2.auditservice.repository.entity;

import by.it_academy.jd2.commonlib.dto.EssenceType;
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
@Table(name = "audits")
public class AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreatedDate
    @Column(name = "dt_create", nullable = false)
    private Instant dtCreate;

    @LastModifiedDate
    @Column(name = "dt_update", nullable = false)
    private Instant dtUpdate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private EssenceType type;

    @Column(name = "essence_id", nullable = false)
    private String essenceId;
}

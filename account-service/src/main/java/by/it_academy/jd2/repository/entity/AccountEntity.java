package by.it_academy.jd2.repository.entity;

import by.it_academy.jd2.service.dto.EType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "accounts")
@Entity
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreatedDate
    @Column(name = "dt_create", nullable = false)
    private Instant dtCreate;

    @LastModifiedDate
    @Column(name = "dt_update", nullable = false)
    private Instant dtUpdate;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EType type;

    @Column(name = "currency_id", nullable = false)
    private UUID currencyId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;                     //непонятно что делать, или таблицу делать!!!!
}


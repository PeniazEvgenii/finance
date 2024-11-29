package by.it_academy.jd2.schedulerservice.repository.entity;

import by.it_academy.jd2.schedulerservice.service.dto.ETimeUnit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "stop_time")
    private Instant stopTime;

    private Long interval;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_unit")
    private ETimeUnit timeUnit;

}

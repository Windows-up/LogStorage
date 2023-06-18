package ru.doctorixx.applogstorage.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class LogReport {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String version;
    @Column(columnDefinition = "TEXT")
    private String report;
    private LocalDateTime datetime;
    private boolean solved;
    private String name = "Unknown problem";
}

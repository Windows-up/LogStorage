package ru.doctorixx.applogstorage.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class LogReport {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String version;
    private String report;
    private LocalDateTime datetime;
}

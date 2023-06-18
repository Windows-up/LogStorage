package ru.doctorixx.applogstorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountReportsCount {
    private Long solved;
    private Long not_solved;
}

package ru.doctorixx.applogstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.doctorixx.applogstorage.domain.LogReport;
import ru.doctorixx.applogstorage.model.LogReportModel;
import ru.doctorixx.applogstorage.repo.ReportsRepo;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/reports")
public class ReportApiRestController {

    @Autowired
    ReportsRepo reportsRepository;

    @PostMapping
    public String add(@RequestBody LogReportModel logReportModel) {
        LogReport logReport = logReportModel.toDomain();
        logReport.setDatetime(LocalDateTime.now());
        reportsRepository.save(logReport);
        return "OK";
    }
}

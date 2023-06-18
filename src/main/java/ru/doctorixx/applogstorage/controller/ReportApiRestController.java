package ru.doctorixx.applogstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.doctorixx.applogstorage.domain.LogReport;
import ru.doctorixx.applogstorage.model.CountReportsCount;
import ru.doctorixx.applogstorage.model.LogReportModel;
import ru.doctorixx.applogstorage.repo.ReportsRepo;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/reports")
public class ReportApiRestController {

    final ReportsRepo reportsRepository;

    @Autowired
    public ReportApiRestController(ReportsRepo reportsRepository) {
        this.reportsRepository = reportsRepository;
    }

    @PostMapping
    public String add(@RequestBody LogReportModel logReportModel) {
        LogReport logReport = logReportModel.toDomain();
        logReport.setDatetime(LocalDateTime.now());
        reportsRepository.save(logReport);
        return "OK";
    }

    @GetMapping
    public CountReportsCount count() {
        return new CountReportsCount(
                reportsRepository.countLogReportsBySolved(true),
                reportsRepository.countLogReportsBySolved(false)
        );
    }
}

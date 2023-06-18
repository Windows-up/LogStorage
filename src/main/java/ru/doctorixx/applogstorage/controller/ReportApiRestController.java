package ru.doctorixx.applogstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.doctorixx.applogstorage.model.CountReportsCount;
import ru.doctorixx.applogstorage.model.LogReportModel;
import ru.doctorixx.applogstorage.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportApiRestController {
    final ReportService reportService;

    @Autowired
    public ReportApiRestController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public String add(@RequestBody LogReportModel logReportModel) {
        reportService.addFromModel(logReportModel);
        return "OK";
    }

    @GetMapping
    public CountReportsCount count() {
        return reportService.getReportsCountWithStats();
    }
}

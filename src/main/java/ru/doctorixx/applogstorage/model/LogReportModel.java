package ru.doctorixx.applogstorage.model;

import lombok.Data;
import ru.doctorixx.applogstorage.domain.LogReport;

@Data
public class LogReportModel {
    private String version;
    private String report;

    public LogReport toDomain() {
        LogReport logReport = new LogReport();
        logReport.setReport(report);
        logReport.setVersion(version);

        return logReport;
    }
}

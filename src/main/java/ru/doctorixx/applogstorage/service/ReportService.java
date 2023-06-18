package ru.doctorixx.applogstorage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.doctorixx.applogstorage.domain.LogReport;
import ru.doctorixx.applogstorage.exception.ReportNotFoundException;
import ru.doctorixx.applogstorage.model.CountReportsCount;
import ru.doctorixx.applogstorage.model.LogReportModel;
import ru.doctorixx.applogstorage.repo.ReportsRepo;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReportService {

    private final ReportsRepo reportsRepository;

    @Autowired
    public ReportService(ReportsRepo reportsRepository) {
        this.reportsRepository = reportsRepository;
    }

    public CountReportsCount getReportsCountWithStats() {
        return new CountReportsCount(
                reportsRepository.countLogReportsBySolved(true),
                reportsRepository.countLogReportsBySolved(false)
        );
    }

    public void addFromModel(LogReportModel logReportModel) {
        LogReport logReport = logReportModel.toDomain();
        logReport.setDatetime(LocalDateTime.now());
        reportsRepository.save(logReport);
    }

    public void changeNameById(Long id, String name) throws ReportNotFoundException {
        Optional<LogReport> optionalReport = reportsRepository.findById(id);
        if (optionalReport.isEmpty()) throw new ReportNotFoundException();

        LogReport report = optionalReport.get();

        report.setName(name);

        reportsRepository.save(report);
    }

    public void changeStatusById(Long id) throws ReportNotFoundException {
        Optional<LogReport> optionalReport = reportsRepository.findById(id);
        if (optionalReport.isEmpty()) throw new ReportNotFoundException();

        LogReport report = optionalReport.get();
        report.setSolved(!report.isSolved());

        reportsRepository.save(report);
    }

    public void deleteById(Long id) throws ReportNotFoundException {
        Optional<LogReport> optionalReport = reportsRepository.findById(id);

        if (optionalReport.isEmpty()) throw new ReportNotFoundException();

        LogReport report = optionalReport.get();

        reportsRepository.delete(report);
    }

    public LogReport findById(Long id) throws ReportNotFoundException{
        Optional<LogReport> optionalReport = reportsRepository.findById(id);

        if (optionalReport.isEmpty()) throw new ReportNotFoundException();

        return optionalReport.get();
    }

    public Iterable<LogReport> findAllBySolvedOrderByDatetimeAsc(boolean solved){
        return reportsRepository.findAllBySolvedOrderByDatetimeAsc(solved);
    }
}

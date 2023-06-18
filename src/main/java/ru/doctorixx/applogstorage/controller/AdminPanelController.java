package ru.doctorixx.applogstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.doctorixx.applogstorage.domain.LogReport;
import ru.doctorixx.applogstorage.exception.ReportNotFoundException;
import ru.doctorixx.applogstorage.model.ChangeNameFormData;
import ru.doctorixx.applogstorage.repo.ReportsRepo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/panel")
public class AdminPanelController {

    private final ReportsRepo reportsRepository;

    @Autowired
    public AdminPanelController(ReportsRepo reportsRepository) {
        this.reportsRepository = reportsRepository;
    }

    @GetMapping
    public String index(Model model) {
        Iterable<LogReport> reports = reportsRepository.findAllBySolvedOrderByDatetimeAsc(false);
        model.addAttribute("reports", reports);
        return "panel";
    }
    @GetMapping("/solved")
    public String index_solved(Model model) {
        Iterable<LogReport> reports = reportsRepository.findAllBySolvedOrderByDatetimeAsc(true);
        model.addAttribute("reports", reports);
        model.addAttribute("solved", true);
        return "panel";
    }

    @GetMapping("/report/{id}")
    public String infoById(@PathVariable Long id, Model model) {
        Optional<LogReport> optionalReport = reportsRepository.findById(id);

        if (optionalReport.isEmpty()) throw new ReportNotFoundException();

        LogReport report = optionalReport.get();
        model.addAttribute("report", report);

        return "check_report";
    }

    @GetMapping("/report/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        Optional<LogReport> optionalReport = reportsRepository.findById(id);

        if (optionalReport.isEmpty()) throw new ReportNotFoundException();

        LogReport report = optionalReport.get();

        reportsRepository.delete(report);

        return "redirect:/panel";
    }

    @GetMapping("/report/{id}/changeStatus")
    public String SolveOrUnsolveById(@PathVariable Long id) {
        Optional<LogReport> optionalReport = reportsRepository.findById(id);
        if (optionalReport.isEmpty()) throw new ReportNotFoundException();

        LogReport report = optionalReport.get();
        report.setSolved(!report.isSolved());

        reportsRepository.save(report);
        return "redirect:/panel/report/%s".formatted(id);
    }

    @PostMapping(value = "/report/{id}/changeName")
    public String changeNameById(@PathVariable Long id, ChangeNameFormData formData) {
        Optional<LogReport> optionalReport = reportsRepository.findById(id);
        if (optionalReport.isEmpty()) throw new ReportNotFoundException();

        LogReport report = optionalReport.get();

        report.setName(formData.getName());

        reportsRepository.save(report);
        return "redirect:/panel/report/%s".formatted(id);
    }
}

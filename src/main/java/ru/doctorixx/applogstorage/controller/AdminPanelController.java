package ru.doctorixx.applogstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.doctorixx.applogstorage.domain.LogReport;
import ru.doctorixx.applogstorage.model.ChangeNameFormData;
import ru.doctorixx.applogstorage.service.ReportService;

@Controller
@RequestMapping("/panel")
public class AdminPanelController {
    private final ReportService reportService;

    @Autowired
    public AdminPanelController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public String index(Model model) {
        Iterable<LogReport> reports = reportService.findAllBySolvedOrderByDatetimeAsc(false);
        model.addAttribute("reports", reports);
        return "panel";
    }

    @GetMapping("/solved")
    public String index_solved(Model model) {
        Iterable<LogReport> reports = reportService.findAllBySolvedOrderByDatetimeAsc(true);
        model.addAttribute("reports", reports);
        model.addAttribute("solved", true);
        return "panel";
    }

    @GetMapping("/report/{id}")
    public String infoById(@PathVariable Long id, Model model) {
        LogReport report = reportService.findById(id);
        model.addAttribute("report", report);

        return "check_report";
    }

    @GetMapping("/report/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        reportService.deleteById(id);
        return "redirect:/panel";
    }

    @GetMapping("/report/{id}/changeStatus")
    public String SolveOrUnSolveById(@PathVariable Long id) {
        reportService.changeStatusById(id);
        return "redirect:/panel/report/%s".formatted(id);
    }

    @PostMapping(value = "/report/{id}/changeName")
    public String changeNameById(@PathVariable Long id, ChangeNameFormData formData) {
        reportService.changeNameById(id, formData.getName());
        return "redirect:/panel/report/%s".formatted(id);
    }
}

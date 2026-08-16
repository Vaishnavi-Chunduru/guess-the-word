package com.example.guess_the_word.controller;

import com.example.guess_the_word.dto.DailyReport;
import com.example.guess_the_word.dto.UserReport;
import com.example.guess_the_word.service.ReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/daily")
    public DailyReport getDailyReport(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        return reportService.getDailyReport(date);
    }


//    @GetMapping("/user/{username}")
//    public List<UserReport> getUserReport(
//            @PathVariable String username) {
//
//        return reportService.getUserReport(username);
//    }
}
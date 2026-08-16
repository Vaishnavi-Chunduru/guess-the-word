package com.example.guess_the_word.controller;

import com.example.guess_the_word.dto.UserReport;
import com.example.guess_the_word.service.ReportService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserReportController {

    private final ReportService reportService;

    public UserReportController(
            ReportService reportService) {

        this.reportService = reportService;
    }

    @GetMapping("/report")
    public List<UserReport> getUserReport(
            Authentication authentication) {

        String username = authentication.getName();

        return reportService.getUserReport(username);
    }
}
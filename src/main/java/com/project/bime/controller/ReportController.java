package com.project.bime.controller;

import com.project.bime.payload.ReportResponse;
import com.project.bime.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/")
    public ResponseEntity<ReportResponse> report() {
        return new ResponseEntity<ReportResponse>(reportService.getReport(), HttpStatus.OK);
    }
}

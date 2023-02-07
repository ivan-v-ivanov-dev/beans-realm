package com.personal.additives.controller;

import com.personal.additives.service.contracts.AdditiveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class AdditiveController {

    private final AdditiveService additiveService;

    public AdditiveController(AdditiveService additiveService) {
        this.additiveService = additiveService;
    }

    @GetMapping
    public String health() {
        return "Healthy";
    }
}

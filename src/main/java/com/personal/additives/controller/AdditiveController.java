package com.personal.additives.controller;

import com.personal.additives.models.Type;
import com.personal.additives.service.contracts.AdditiveService;
import com.personal.additives.service.contracts.TypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdditiveController {

    private final AdditiveService additiveService;
    private final TypeService typeService;

    public AdditiveController(AdditiveService additiveService,
                              TypeService typeService) {
        this.additiveService = additiveService;
        this.typeService = typeService;
    }

    @GetMapping("/types")
    public List<Type> findAllTypes() {
        return typeService.findAllOrderedByNameAsc();
    }

}

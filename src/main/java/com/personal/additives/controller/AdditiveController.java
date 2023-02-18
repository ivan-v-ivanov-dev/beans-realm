package com.personal.additives.controller;

import com.personal.additives.models.Tag;
import com.personal.additives.models.Type;
import com.personal.additives.service.contracts.AdditiveService;
import com.personal.additives.service.contracts.TagService;
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
    private final TagService tagService;

    public AdditiveController(AdditiveService additiveService,
                              TypeService typeService,
                              TagService tagService) {
        this.additiveService = additiveService;
        this.typeService = typeService;
        this.tagService = tagService;
    }

    @GetMapping("/types")
    public List<Type> findAllTypes() {
        return typeService.findAllOrderedByNameAsc();
    }

    @GetMapping("/tags")
    public List<Tag> findAllTags() {
        return this.tagService.findAllOrderedByNameAsc();
    }
}

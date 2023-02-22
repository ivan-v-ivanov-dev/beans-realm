package com.personal.additives.controller;

import com.personal.additives.service.contracts.AdditiveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdditiveController {

    private final AdditiveService additiveService;

    public AdditiveController(AdditiveService additiveService) {
        this.additiveService = additiveService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("latestBeans", this.additiveService.latest());
        model.addAttribute("mostDownloadedBeans", this.additiveService.mostDownloaded());
        model.addAttribute("topRatedAdditives", this.additiveService.topRated());

        return "index";
    }

}

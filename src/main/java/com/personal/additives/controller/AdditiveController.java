package com.personal.additives.controller;

import com.personal.additives.service.contracts.AdditiveService;
import com.personal.additives.service.contracts.UserService;
import com.personal.additives.service.contracts.VersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdditiveController {

    private final AdditiveService additiveService;
    private final VersionService versionService;
    private final UserService userService;

    public AdditiveController(AdditiveService additiveService,
                              VersionService versionService,
                              UserService userService) {
        this.additiveService = additiveService;
        this.versionService = versionService;
        this.userService = userService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("latestBeans", this.additiveService.latest());
        model.addAttribute("mostDownloadedBeans", this.additiveService.mostDownloaded());
        model.addAttribute("topRatedAdditives", this.additiveService.topRated());

        return "index";
    }

    @ModelAttribute("populateBeansCount")
    public int beansCount() {
        return this.additiveService.beansCount();
    }

    @ModelAttribute("populateTotalDownloadsCount")
    public int totalDownloadsCount() {
        return this.versionService.totalDownloadCount();
    }

    @ModelAttribute("populateUsersCount")
    public int usersCount() {
        return userService.userCount();
    }

    // TODO: IMPLEMENT totalComments Count
}

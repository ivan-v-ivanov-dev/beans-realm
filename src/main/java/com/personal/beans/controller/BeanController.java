package com.personal.beans.controller;

import com.personal.beans.service.contracts.BeanService;
import com.personal.beans.service.contracts.UserService;
import com.personal.beans.service.contracts.VersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BeanController {

    private final BeanService beanService;
    private final VersionService versionService;
    private final UserService userService;

    public BeanController(BeanService beanService,
                          VersionService versionService,
                          UserService userService) {
        this.beanService = beanService;
        this.versionService = versionService;
        this.userService = userService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("latestBeans", this.beanService.latest());
        model.addAttribute("mostDownloadedBeans", this.beanService.mostDownloaded());
        model.addAttribute("topRatedBeans", this.beanService.topRated());

        return "index";
    }

    @ModelAttribute("populateBeansCount")
    public int beansCount() {
        return this.beanService.beansCount();
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

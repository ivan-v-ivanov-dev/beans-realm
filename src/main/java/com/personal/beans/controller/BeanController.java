package com.personal.beans.controller;

import com.personal.beans.service.contracts.BeanService;
import com.personal.beans.service.contracts.CommentService;
import com.personal.beans.service.contracts.UserService;
import com.personal.beans.service.contracts.VersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BeanController {

    private final BeanService beanService;
    private final VersionService versionService;
    private final UserService userService;
    private final CommentService commentService;

    public BeanController(BeanService beanService,
                          VersionService versionService,
                          UserService userService,
                          CommentService commentService) {
        this.beanService = beanService;
        this.versionService = versionService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("latestBeans", this.beanService.latest());
        model.addAttribute("mostDownloadedBeans", this.beanService.mostDownloaded());
        model.addAttribute("topRatedBeans", this.beanService.topRated());
        model.addAttribute("totalBeansCount", this.beanService.beansCount());
        model.addAttribute("totalDownloadsCount", this.versionService.totalDownloadCount());
        model.addAttribute("totalUsersCount", this.userService.userCount());
        model.addAttribute("totalCommentsCount", this.commentService.count());
        return "index";
    }

    @GetMapping("beans/{beanName}")
    public String beanDetails(@PathVariable String beanName, Model model) {
        model.addAttribute("currentBean", this.beanService.findByName(beanName));
        model.addAttribute("currentBeanVersions", this.versionService.findByBean(beanName));
        return "bean";
    }

}

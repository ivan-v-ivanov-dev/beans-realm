package com.personal.beans.controller;

import com.personal.beans.models.dto.BeanDto;
import com.personal.beans.models.dto.CommentDto;
import com.personal.beans.service.contracts.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Controller
public class BeanController {

    private final BeanService beanService;
    private final VersionService versionService;
    private final UserService userService;
    private final CommentService commentService;
    private final TagService tagService;
    private final TypeService typeService;
    private final DeviceService deviceService;

    public BeanController(BeanService beanService,
                          VersionService versionService,
                          UserService userService,
                          CommentService commentService,
                          TagService tagService,
                          TypeService typeService,
                          DeviceService deviceService) {
        this.beanService = beanService;
        this.versionService = versionService;
        this.userService = userService;
        this.commentService = commentService;
        this.tagService = tagService;
        this.typeService = typeService;
        this.deviceService = deviceService;
    }

    @GetMapping("/")
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

    @GetMapping("/bean/{beanName}")
    public String beanDetails(@PathVariable String beanName, Model model) {
        model.addAttribute("currentBean", this.beanService.findByName(beanName));
        model.addAttribute("currentBeanVersions", this.versionService.findByBean(beanName));
        model.addAttribute("totalComment", this.commentService.findByBean(beanName));
        model.addAttribute("totalVersionsCount", this.versionService.countByBean(beanName));
        model.addAttribute("totalCommentsCount", this.commentService.countByBeanName(beanName));
        model.addAttribute("commentDto", CommentDto.builder().build());
        return "bean";
    }

    @RolesAllowed({"authorised-user", "admin"})
    @PostMapping("/bean/{beanName}/comment")
    public String postComment(@PathVariable String beanName,
                              @Valid @ModelAttribute("commentDto") CommentDto commentDto,
                              BindingResult errors, Model model) {
        model.addAttribute("currentBean", this.beanService.findByName(beanName));
        model.addAttribute("currentBeanVersions", this.versionService.findByBean(beanName));
        model.addAttribute("totalComment", this.commentService.findByBean(beanName));
        model.addAttribute("totalVersionsCount", this.versionService.countByBean(beanName));
        model.addAttribute("totalCommentsCount", this.commentService.countByBeanName(beanName));
        model.addAttribute("commentDto", CommentDto.builder().beanName(beanName).build());

        if (errors.hasErrors()) {
            return "bean";
        }
        this.commentService.save(beanName, commentDto);
        return "redirect:/bean/" + beanName;
    }

    @GetMapping("/beans")
    public String filter(Model model) {
        model.addAttribute("tags", this.tagService.findAll());
        model.addAttribute("types", this.typeService.findAll());
        model.addAttribute("devices", this.deviceService.findAll());
        return "beans-filter-empty";
    }

    @PostMapping("/beans")
    public String filterBeans(@RequestParam(required = false) Integer tag,
                              @RequestParam(required = false) Integer type,
                              @RequestParam(required = false) Integer device,
                              @RequestParam Integer offset, Model model) {
        model.addAttribute("tags", this.tagService.findAll());
        model.addAttribute("types", this.typeService.findAll());
        model.addAttribute("devices", this.deviceService.findAll());
        model.addAttribute("filteredBeans", this.beanService.filter(tag, type, device, offset));
        return "beans-filter-populated";
    }

    @RolesAllowed({"authorised-user", "admin"})
    @GetMapping("/bean")
    public String create(Model model) {
        model.addAttribute("tags", this.tagService.findAll());
        model.addAttribute("types", this.typeService.findAll());
        model.addAttribute("devices", this.deviceService.findAll());
        model.addAttribute("beanDto", new BeanDto());
        return "bean-create";
    }

    @RolesAllowed({"authorised-user", "admin"})
    @PostMapping("/bean")
    public String createBean(@Valid @ModelAttribute("beanDto") BeanDto beanDto,
                             BindingResult errors, Model model) {
        model.addAttribute("tags", this.tagService.findAll());
        model.addAttribute("types", this.typeService.findAll());
        model.addAttribute("devices", this.deviceService.findAll());

        if (errors.hasErrors()) {
            return "bean-create";
        }

        this.beanService.create(beanDto);
        return "redirect:/";
    }

    @RolesAllowed("admin")
    @GetMapping("/beans/unapproved")
    public String approveBeans(Model model) {
        model.addAttribute("notApprovedBeans", this.beanService.notApproved());
        return "beans-not-approved";
    }

    @RolesAllowed("admin")
    @GetMapping("/bean/{beanName}/versions/unapproved")
    public String unapprovedVersionsForBean(@PathVariable String beanName, Model model) {
        model.addAttribute("notApprovedVersions", this.versionService.notApprovedByBean(beanName));
        model.addAttribute("beanNameFromModel", beanName);
        return "bean-versions-not-approved";
    }

    @RolesAllowed("admin")
    @PostMapping("/bean/version/approve")
    public String approveVersionForBean(@RequestParam("versionName") String versionName,
                                        @RequestParam("beanName") String beanName) {
        this.versionService.approveByBean(versionName, beanName);
        return "redirect:/bean/" + beanName + "/versions/unapproved";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", this.userService.findAll());
        model.addAttribute("usersCount", this.userService.userCount());
        return "users";
    }

}

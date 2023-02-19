package com.personal.additives.controller;

import com.personal.additives.models.Device;
import com.personal.additives.models.Tag;
import com.personal.additives.models.Type;
import com.personal.additives.models.User;
import com.personal.additives.service.contracts.DeviceService;
import com.personal.additives.service.contracts.TagService;
import com.personal.additives.service.contracts.TypeService;
import com.personal.additives.service.contracts.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdditiveController {

    private final TypeService typeService;
    private final TagService tagService;
    private final DeviceService deviceService;
    private final UserService userService;

    public AdditiveController(TypeService typeService,
                              TagService tagService,
                              DeviceService deviceService,
                              UserService userService) {
        this.typeService = typeService;
        this.tagService = tagService;
        this.deviceService = deviceService;
        this.userService = userService;
    }

    @GetMapping("/types")
    public List<Type> findAllTypes() {
        return typeService.findAll();
    }

    @GetMapping("/tags")
    public List<Tag> findAllTags() {
        return this.tagService.findAll();
    }

    @GetMapping("/devices")
    public List<Device> findAllDevices() {
        return this.deviceService.findAll();
    }

    @PostMapping("/users")
    public List<User> findAllUsers(@RequestParam(required = false) boolean enabled) {
        return this.userService.findAllUsers(enabled);
    }

}

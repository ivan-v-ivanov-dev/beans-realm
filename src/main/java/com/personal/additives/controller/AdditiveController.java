package com.personal.additives.controller;

import com.personal.additives.models.*;
import com.personal.additives.service.contracts.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdditiveController {

    private final TypeService typeService;
    private final TagService tagService;
    private final DeviceService deviceService;
    private final UserService userService;
    private final AdditiveService additiveService;

    public AdditiveController(TypeService typeService,
                              TagService tagService,
                              DeviceService deviceService,
                              UserService userService,
                              AdditiveService additiveService) {
        this.typeService = typeService;
        this.tagService = tagService;
        this.deviceService = deviceService;
        this.userService = userService;
        this.additiveService = additiveService;
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
    public List<User> findAllUsers(@RequestParam(required = false) boolean enabled,
                                   @RequestParam(required = false) String username) {
        return this.userService.findAllUsers(enabled, username);
    }

    @PostMapping("/users/create")
    public User createUser(@RequestBody User user) {
        return this.userService.create(user);
    }

    @PutMapping("/users/update")
    public User updateUser(@RequestBody User user) {
        return this.userService.update(user);
    }

    @DeleteMapping("/users/delete")
    public void deleteUser(@RequestBody User user) {
        this.userService.delete(user);
    }

    @PostMapping("/additive/create")
    public Additive createAdditive(@RequestBody Additive additive) {
        return this.additiveService.create(additive);
    }

}

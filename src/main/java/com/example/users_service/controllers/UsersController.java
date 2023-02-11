package com.example.users_service.controllers;

import com.example.users_service.DTOs.UserDto;
import com.example.users_service.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/users")
    public void createUser(@RequestBody UserDto userDto) {
        usersService.createUser(userDto);
        log.info("User {} is created.", userDto);
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return usersService.getAllUsers();
    }
}

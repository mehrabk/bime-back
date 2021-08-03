package com.project.bime.controller;

import com.project.bime.payload.user.UserSummary;
import com.project.bime.security.CurrentUser;
import com.project.bime.security.UserDetailsImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/me")
    public UserSummary getCurrentUser(@CurrentUser UserDetailsImpl userDetails) {
        UserSummary userSummary = new UserSummary(userDetails.getUsername(), userDetails.getEmail(), userDetails.getAuthorities().stream().map(role -> role.getAuthority()).collect(Collectors.toSet()));
        return userSummary;
    }
}

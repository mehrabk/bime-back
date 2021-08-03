package com.project.bime.payload.user;

import com.project.bime.model.user.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserSummary {

    private String username;
    private String email;
    private Set<String> roles = new HashSet<>();

    public UserSummary(String username, String email, Set<String> roles) {
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}

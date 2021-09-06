package com.project.bime.payload.user;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

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

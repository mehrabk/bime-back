package com.project.bime.payload;

import lombok.Data;

import java.util.List;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}

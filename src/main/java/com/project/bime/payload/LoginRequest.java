package com.project.bime.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

//    @NotBlank
//    @Size(min = 10)
//    private String captcha;
}

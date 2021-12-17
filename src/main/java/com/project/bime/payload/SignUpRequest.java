package com.project.bime.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {

    @NotBlank
    @Size(min = 3, max = 40)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

//    @NotBlank
//    @Size(min = 10)
//    private String captcha;

}

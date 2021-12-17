package com.project.bime.payload.customer;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CustomerRequest {

    @NotNull
    @Min(0)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 25, message = "Username must be between 2 and 25 length")
    private String userName;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 25, message = "Lastname must be between 2 and 25 length")
    private String lastName;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50, message = "Address must be between 2 and 25 length")
    private String address;

    @NotNull
    @NotBlank
    @Pattern(regexp="[\\d]{11}", message = "PhoneNumber must be 11 digit - Like: 09xx-xxx-xxxx")
    private String phoneNumber;

    @NotNull
    @NotBlank
    @Pattern(regexp="[\\d]{10}", message = "National code must be 10 digit")
    private String nationalCode;
}

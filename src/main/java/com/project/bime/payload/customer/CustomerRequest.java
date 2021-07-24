package com.project.bime.payload.customer;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CustomerRequest {
    private long id;
    @NotNull @NotBlank @Size(min = 3, max = 25)
    private String userName;
    @NotNull @NotBlank
    @Size(min = 1, max = 25)
    private String lastName;
    @NotNull @NotBlank
    private String address;
    @NotNull @NotBlank
    private String phoneNumber;
    @NotNull @NotBlank @Size(min = 10, max = 10)
    private String nationalCode;
}

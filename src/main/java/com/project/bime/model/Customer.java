package com.project.bime.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class Customer extends AuditModel {
    public Customer(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 25)
    private String userName;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 25)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String address;

    @NotNull
    @NotBlank
    @Pattern(regexp="[\\d]{11}")
    private String phoneNumber;

    @NotNull
    @NotBlank
    @Pattern(regexp="[\\d]{10}")
    private String nationalCode;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Bime> bimeList;

}

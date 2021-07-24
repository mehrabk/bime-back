package com.project.bime.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class Customer extends AuditModel {
    public Customer(){}

    public Customer(String userName, String lastName, String address, String phoneNumber, String nationalCode) {
        this.userName = userName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.nationalCode = nationalCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String userName;

    @NotNull
    @Size(min = 1, max = 25)
    private String lastName;

    @NotNull
    @Size(min = 1, max = 40)
    private String address;

    @NotNull
    @Size(min = 3, max = 25)
    private String phoneNumber;

    @NotNull
    @Size(min = 3, max = 25)
    private String nationalCode;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Bime> bimeList;

}

package com.project.bime.payload.customer;

import com.project.bime.model.Customer;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerResponse {

    private Long id;
    private String userName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String nationalCode;

    // Extra information
    private Date createAt;
    private Date updateAt;
    private int bimeCount;

    public CustomerResponse(Customer customer) {
        setId(customer.getId());
        setUserName(customer.getUserName());
        setLastName(customer.getLastName());
        setAddress(customer.getAddress());
        setPhoneNumber(customer.getPhoneNumber());
        setNationalCode(customer.getNationalCode());
        setBimeCount(customer.getBimeList() != null ? customer.getBimeList().size() : 0);
        setCreateAt(customer.getCreatedAt());
        setUpdateAt(customer.getUpdatedAt());
    }
}

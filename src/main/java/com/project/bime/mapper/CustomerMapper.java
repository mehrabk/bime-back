package com.project.bime.mapper;

import com.project.bime.model.Customer;
import com.project.bime.payload.PagedResponse;
import com.project.bime.payload.customer.CustomerRequest;
import com.project.bime.payload.customer.CustomerResponse;
import com.project.bime.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerMapper(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public PagedResponse<CustomerResponse> toPagedResponse(Page<Customer> items) {
        if (items.getNumberOfElements() == 0) {
            return new PagedResponse<CustomerResponse>(Collections.emptyList(), items.getNumber(), items.getSize(),
                    items.getTotalElements(), items.getTotalPages(), items.isLast());
        }

        return new PagedResponse<CustomerResponse>(items.getContent().stream().map(c -> new CustomerResponse(c)).collect(Collectors.toList()),
                items.getNumber(), items.getSize(), items.getTotalElements(), items.getTotalPages(), items.isLast());
    }

    public Customer requestToCustomer(CustomerRequest request, Customer newCustomer) {
        newCustomer.setUserName(request.getUserName());
        newCustomer.setLastName(request.getLastName());
        newCustomer.setAddress(request.getAddress());
        newCustomer.setPhoneNumber(request.getPhoneNumber());
        newCustomer.setNationalCode(request.getNationalCode());
        return newCustomer;
    }
}

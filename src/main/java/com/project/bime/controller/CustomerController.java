package com.project.bime.controller;

import com.project.bime.exception.NotFoundException;
import com.project.bime.model.Customer;
import com.project.bime.payload.ApiResponse;
import com.project.bime.payload.PagedResponse;
import com.project.bime.payload.customer.CustomerRequest;
import com.project.bime.payload.customer.CustomerResponse;
import com.project.bime.payload.shared.SearchPageInfo;
import com.project.bime.repository.CustomerRepository;
import com.project.bime.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private CustomerService customerService;
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/list")
    public PagedResponse<CustomerResponse> getAllByQuery(SearchPageInfo search) {
        return customerService.findAllByQuery(search);
    }

    @PostMapping("/save") // add and edit
    public ResponseEntity<CustomerResponse> save(@Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(new CustomerResponse(customerService.save(request)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer with id=" + id + " Not Found!"));
        try {
            customerRepository.delete(customer);
            return ResponseEntity.ok(new ApiResponse(true, "Customer with id=" + id + " Deleted!"));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ApiResponse(false, "Error : " + ex.getMessage() ), HttpStatus.BAD_REQUEST);
        }
    }
}

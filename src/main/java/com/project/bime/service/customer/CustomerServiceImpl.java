package com.project.bime.service.customer;

import com.project.bime.mapper.CustomerMapper;
import com.project.bime.model.Customer;
import com.project.bime.payload.PagedResponse;
import com.project.bime.payload.customer.CustomerRequest;
import com.project.bime.payload.customer.CustomerResponse;
import com.project.bime.payload.shared.SearchPageInfo;
import com.project.bime.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public PagedResponse<CustomerResponse> findAll(Pageable pageable) {
        Page<Customer> items = customerRepository.findAll(pageable);
        return customerMapper.toPagedResponse(items);
    }

    @Override
    public PagedResponse<CustomerResponse> findAllByQuery(SearchPageInfo search) {
        Pageable pageable = search.getPageable();
        String query = search.getQuery() == null ? null : search.getQuery().trim();
        if (search.getQuery() == null || query.length() == 0) {
            return this.findAll(pageable);
        }
        Page<Customer> items = customerRepository.findAllByQuery(query, pageable);
        return customerMapper.toPagedResponse(items);
    }

    @Override
    public Customer save(CustomerRequest request) {
        Optional<Customer> item = request.getId() > 0 ? customerRepository.findById(request.getId()) : Optional.empty();
        Customer newCustomer = item.orElseGet(() -> new Customer());
        return customerRepository.save(customerMapper.requestToCustomer(request, newCustomer));
    }


}

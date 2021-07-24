package com.project.bime.service.customer;

import com.project.bime.model.Customer;
import com.project.bime.payload.PagedResponse;
import com.project.bime.payload.customer.CustomerRequest;
import com.project.bime.payload.customer.CustomerResponse;
import com.project.bime.payload.shared.SearchPageInfo;
import org.springframework.data.domain.Pageable;


public interface CustomerService {
    PagedResponse<CustomerResponse> findAll(Pageable pageable);
    PagedResponse<CustomerResponse> findAllByQuery(SearchPageInfo search);
    Customer save(CustomerRequest request);
}

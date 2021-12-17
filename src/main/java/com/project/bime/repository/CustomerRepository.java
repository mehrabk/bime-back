package com.project.bime.repository;

import com.project.bime.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c where c.userName LIKE %:query% OR c.lastName LIKE %:query% OR c.nationalCode Like %:query%")
    Page<Customer> findAllByQuery(@Param("query") String query, Pageable pageable);
}

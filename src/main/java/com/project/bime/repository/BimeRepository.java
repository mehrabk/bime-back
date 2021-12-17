package com.project.bime.repository;

import com.project.bime.model.Bime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BimeRepository extends JpaRepository<Bime, Long> {

//    Page<Bime> findAllByCustomer_Id(Pageable pageable, long customerId);
    @Query("SELECT b FROM Bime b where b.customer.id = :customerId")
    Page<Bime> findAllByCustomer_Id(@Param("customerId") long customerId, Pageable pageable);
}

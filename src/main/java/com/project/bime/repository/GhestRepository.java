package com.project.bime.repository;

import com.project.bime.model.Ghest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GhestRepository extends JpaRepository<Ghest, Long> {
    Page<Ghest> findAllByBime_Id(Pageable pageable, long bimeId);
}

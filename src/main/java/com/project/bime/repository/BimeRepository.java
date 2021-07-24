package com.project.bime.repository;

import com.project.bime.model.Bime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BimeRepository extends JpaRepository<Bime, Long> {
}

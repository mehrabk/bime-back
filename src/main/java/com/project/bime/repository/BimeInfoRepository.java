package com.project.bime.repository;

import com.project.bime.model.BimeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BimeInfoRepository extends JpaRepository<BimeInfo, Long> {
}

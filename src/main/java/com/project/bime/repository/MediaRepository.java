package com.project.bime.repository;

import com.project.bime.model.extra.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Attachment, Long> {
}

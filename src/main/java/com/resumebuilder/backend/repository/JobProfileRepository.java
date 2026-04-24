package com.resumebuilder.backend.repository;

import com.resumebuilder.backend.model.JobProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobProfileRepository extends JpaRepository<JobProfile, Long> {
    List<JobProfile> findByUserId(Long userId);
}

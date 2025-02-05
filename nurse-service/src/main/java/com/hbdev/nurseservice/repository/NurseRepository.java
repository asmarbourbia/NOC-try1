package com.hbdev.nurseservice.repository;

import com.hbdev.nurseservice.entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Long> {
    Nurse findByUserId(Long userId);
}

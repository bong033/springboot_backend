package org.example.repository;

import org.example.model.Nurse;
import org.example.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NurseRepository extends JpaRepository<Nurse,Long> {
    Optional<Nurse> findByUser_UserId(Long userId);
}

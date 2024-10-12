package org.example.repository;

import org.example.model.Package;
import org.example.model.Patient;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Optional<Patient> findByUser_UserId(Long userId);
//    Optional<Patient> findPatientByUserId(Long userId);
    //Data Persistence: CrudRepository (Basic), JpaRepository (Advanced, extends from CrudRepository)
    //save()
    //findOne()
    //findById()
    //findByEmail()
    //findAll()
    //count()
    //delete()
    //deleteById()
}

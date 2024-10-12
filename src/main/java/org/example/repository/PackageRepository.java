package org.example.repository;

import org.example.model.Nurse;
import org.example.model.Package;
import org.example.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PackageRepository extends JpaRepository<Package,Long> {

}

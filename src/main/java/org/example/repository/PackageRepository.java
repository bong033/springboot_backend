package org.example.repository;

import org.example.model.Nurse;
import org.example.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package,Long> {
}

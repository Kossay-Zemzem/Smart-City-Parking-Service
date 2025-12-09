package com.smartcity.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcity.parking.models.Etage;

import java.util.Optional;

public interface EtageRepository extends JpaRepository<Etage, Long> {
    Optional<Etage> findByLabel(String label);
}


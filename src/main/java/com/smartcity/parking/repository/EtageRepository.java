package com.smartcity.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcity.parking.models.Etage;

import java.util.List;
import java.util.Optional;

public interface EtageRepository extends JpaRepository<Etage, Long> {
    Etage findByLabel(String label);
    //Return all etages ordered by their level ascending
    List<Etage> findAllByOrderByLabelAsc();
}


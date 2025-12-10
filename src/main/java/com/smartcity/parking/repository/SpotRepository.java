package com.smartcity.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcity.parking.models.Spot;

public interface SpotRepository extends JpaRepository<Spot, Long> {
    Spot findFirstByCode(String code);
    Spot findByCode(String code);

}


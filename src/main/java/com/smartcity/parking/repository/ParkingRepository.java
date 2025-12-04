package com.smartcity.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcity.parking.models.Parking;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
}


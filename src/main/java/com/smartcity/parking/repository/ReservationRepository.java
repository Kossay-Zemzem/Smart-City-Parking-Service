package com.smartcity.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcity.parking.models.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}


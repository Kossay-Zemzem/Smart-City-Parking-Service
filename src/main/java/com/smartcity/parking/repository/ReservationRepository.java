package com.smartcity.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcity.parking.models.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByRefClient(String refClient);
}


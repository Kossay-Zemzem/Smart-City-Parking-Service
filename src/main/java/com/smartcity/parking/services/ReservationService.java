package com.smartcity.parking.services;

import org.springframework.stereotype.Service;
import com.smartcity.parking.repository.ReservationRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepo;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepo = reservationRepository;
    }

    // TODO: Add business methods
}


package com.smartcity.parking.services;

import com.smartcity.parking.models.Reservation;
import org.springframework.stereotype.Service;
import com.smartcity.parking.repository.ReservationRepository;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepo;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepo = reservationRepository;
    }

    public void recordReservation(Reservation reservation) {
        reservationRepo.save(reservation);
    }

    public List<Reservation> getReservationsByRefClient(String refClient) {
        return reservationRepo.findByRefClient(refClient);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepo.findAll();
    }

    // TODO: Add business methods
}


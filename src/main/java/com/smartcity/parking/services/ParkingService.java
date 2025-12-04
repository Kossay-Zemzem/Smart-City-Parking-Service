package com.smartcity.parking.services;

import org.springframework.stereotype.Service;
import com.smartcity.parking.repository.ParkingRepository;

@Service
public class ParkingService {
    private final ParkingRepository parkingRepo;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepo = parkingRepository;
    }

    // TODO: Add business methods
}


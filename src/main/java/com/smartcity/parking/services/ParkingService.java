package com.smartcity.parking.services;

import com.smartcity.parking.models.Etage;
import com.smartcity.parking.models.Spot;
import com.smartcity.parking.repository.EtageRepository;
import org.springframework.stereotype.Service;
import com.smartcity.parking.repository.ParkingRepository;

import java.util.List;

@Service
public class ParkingService {
    private final ParkingRepository parkingRepo;
    private final EtageRepository etageRepo;

    public ParkingService(ParkingRepository parkingRepository, EtageRepository etageRepo) {
        this.parkingRepo = parkingRepository;
        this.etageRepo = etageRepo;
    }

    public Spot getClosestEmptySpot() { //To revise later
        Spot FoundSpot = null;
        List<Etage> etages = etageRepo.findAllByOrderByLabelAsc();
        for (Etage etage : etages) {
            for (Spot spot : etage.getSpots()) {
                if (!spot.isOccupied()) {
                    FoundSpot.setType(spot.getType());
                    FoundSpot.setCode(spot.getCode());
                    FoundSpot.setEtage(spot.getEtage());
                    return FoundSpot;
                }
            }
        }
        return null;
    }
}
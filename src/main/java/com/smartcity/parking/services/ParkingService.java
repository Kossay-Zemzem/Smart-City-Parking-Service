package com.smartcity.parking.services;

import com.smartcity.parking.models.*;
import com.smartcity.parking.repository.EtageRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.smartcity.parking.repository.ParkingRepository;

import java.util.List;

@Service
public class ParkingService {
    private static final Double PRIX_HEURE_BASE = 2.0; // 2DT
    private static final Logger LG = LogManager.getLogger();

    private final ParkingRepository parkingRepo;
    private final EtageRepository etageRepo;
    private final SpotService spotService;
    private final ReservationService reservationService;

    public ParkingService(ParkingRepository parkingRepository, EtageRepository etageRepo, SpotService spotService, ReservationService reservationService) {
        this.parkingRepo = parkingRepository;
        this.etageRepo = etageRepo;
        this.spotService = spotService;
        this.reservationService = reservationService;
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

    public Facture reserveSpot(Reservation reservation) {
        //locate the right spot in the right etage using spotCode and EtageLabel
        Spot spot = spotService.findSpot(reservation.getSpotCode(), reservation.getEtageLabel());
        if (spot == null || spot.isOccupied()) {
            LG.warn("Spot not found or already occupied: {}", reservation.getSpotCode());
            return null; // Spot not found or already occupied
        }
        spot.setOccupied(true);
        spotService.updateSpot(spot);
        Facture facture = new Facture();
        facture.setSpot(spot);
        facture.setDurationInHours(reservation.getDurationInHours());
        facture.setEtageLabel(spot.getEtage().getLabel());
        facture.setPrixTTC(calculatePrice(spot, reservation.getDurationInHours()));
        facture.setRefClient(reservation.getRefClient());
        facture.setRefFacture("FACT-PARK-" + System.currentTimeMillis());
//        parkingRepo.save(facture); TODO save facture temporarly somewhere
        //Enregistrer la reservation
        reservationService.recordReservation(reservation);
        return facture;
    }

    private Double calculatePrice(Spot spot, Integer durationInHours) {
        if (spot.getType() == ParkingSpotType.VIP) {
            return durationInHours * PRIX_HEURE_BASE * 2.0; //2x the base price for VIP
        } else {
            return durationInHours * PRIX_HEURE_BASE;
        }
    }

}
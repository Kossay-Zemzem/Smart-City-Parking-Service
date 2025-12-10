package com.smartcity.parking.services;

import com.smartcity.parking.models.Etage;
import com.smartcity.parking.models.Spot;
import org.springframework.stereotype.Service;
import com.smartcity.parking.repository.SpotRepository;

import java.util.Set;

@Service
public class SpotService {

    private final SpotRepository spotRepo;
    private final EtageService etageService;

    public SpotService(SpotRepository spotRepository, EtageService etageService) {
        this.spotRepo = spotRepository;
        this.etageService = etageService;
    }

    // TODO: Add business methods

    public Spot getSpotById(Long id) {
        return spotRepo.findById(id).orElse(null);
    }

    public Spot findSpot(String codeSpot,String EtageLabel) {
        Etage etageDuSpot = etageService.getEtageByLabel(EtageLabel);
        if (etageDuSpot == null) {
            return null;
        }else {
            Set<Spot> spots = etageDuSpot.getSpots();
            for (Spot spot : spots) {
                if (spot.getCode().equals(codeSpot)) {
                    return spot;
                }
            }
            return null; // Spot not found
        }

    }

    public void updateSpot(Spot spot) {
        spotRepo.save(spot);
    }

    public Spot getSpotByCode(String code) {
            return spotRepo.findByCode(code);
    }
}


package com.smartcity.parking.services;

import com.smartcity.parking.models.Spot;
import org.springframework.stereotype.Service;
import com.smartcity.parking.repository.SpotRepository;

@Service
public class SpotService {

    private final SpotRepository spotRepo;

    public SpotService(SpotRepository spotRepository) {
        this.spotRepo = spotRepository;
    }

    // TODO: Add business methods

    public Spot getSpotById(Long id) {
        return spotRepo.findById(id).orElse(null);
    }
}


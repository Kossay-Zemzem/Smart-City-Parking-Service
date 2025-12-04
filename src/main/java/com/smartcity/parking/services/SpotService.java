package com.smartcity.parking.services;

import org.springframework.stereotype.Service;
import com.smartcity.parking.repository.SpotRepository;

@Service
public class SpotService {

    private final SpotRepository spotRepo;

    public SpotService(SpotRepository spotRepository) {
        this.spotRepo = spotRepository;
    }

    // TODO: Add business methods
}


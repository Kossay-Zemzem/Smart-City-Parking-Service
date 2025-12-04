package com.smartcity.parking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smartcity.parking.repository.LocationRepository;

@Service
public class LocationService {
    private final LocationRepository locationRepo;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepo = locationRepository;
    }

    // TODO: Add business methods
}

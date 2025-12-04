package com.smartcity.parking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smartcity.parking.repository.EtageRepository;

@Service
public class EtageService {
    private final EtageRepository etageRepo;

    @Autowired
    public EtageService(EtageRepository etageRepository) {
        this.etageRepo = etageRepository;
    }

    // TODO: Add business methods
}


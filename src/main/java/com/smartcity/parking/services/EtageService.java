package com.smartcity.parking.services;

import com.smartcity.parking.models.Etage;
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

    public Etage getEtageById(Long id) {
        return etageRepo.findById(id).orElse(null);
    }

}


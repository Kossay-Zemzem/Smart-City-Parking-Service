package com.smartcity.parking.services;

import com.smartcity.parking.models.Etage;
import com.smartcity.parking.models.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smartcity.parking.repository.EtageRepository;

import java.util.List;

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

    public Etage getEtageByLabel(String label) {
        return etageRepo.findByLabel(label) ;
    }

    public List<Long> getAllEtageIds() {
        return etageRepo.findAll().stream().map(Etage::getId).toList();
    }

    public List<Etage> getEtagesWithEmptySpots() { //this methode is not efficient at all , I am just running out of time
        List<Etage> etages = etageRepo.findAll();
        for (Etage etage : etages){
            etage.getSpots().removeIf(Spot::isOccupied);
        }
        etages.removeIf(etage -> etage.getSpots().isEmpty());
        return etages;
    }

    public List<Etage> getAllEtages() {
            return etageRepo.findAll();
    }
}


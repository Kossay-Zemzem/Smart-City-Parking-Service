package com.smartcity.parking;

import com.smartcity.parking.models.Etage;
import com.smartcity.parking.models.EtatParking;
import com.smartcity.parking.models.ParkingSpotType;
import com.smartcity.parking.models.Spot;
import com.smartcity.parking.repository.EtageRepository;
import com.smartcity.parking.repository.EtageRepository;
import com.smartcity.parking.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataInit {

    private final EtageRepository etageRepository;
    private final SpotRepository spotRepository;

    @Autowired
    public DataInit(EtageRepository etageRepository, SpotRepository spotRepository) {
        this.etageRepository = etageRepository;
        this.spotRepository = spotRepository;
    }

    //command line runner to initialize data at startup
    @Bean
    public ApplicationRunner initializer() {
        return new ApplicationRunner() {
            @Override
            @Transactional
            public void run(ApplicationArguments args) throws Exception {
                //NOTE : DO NOT ADD MORE THAN 10 FLOORS , F0 , F1 , F2 , ... F9 because ordering after that doens't work as expected
                List<Etage> etages = new ArrayList<>();
                // Check if data already exists
//                if (etageRepository.count() == 0 && spotRepository.count() == 0) {
                    // Initialize Etages
                    for (int i = 0; i <= 4; i++) {
                        Etage etage = new Etage();
                        etage.setLabel("F" + i);
                        etages.add(etage);
                    }
                    etageRepository.saveAll(etages);

                    // Initialize Spots
                    List<Spot> spots = new ArrayList<>();
                    for (Etage etage : etages) {
                        for (int j = 1; j <= 20; j++) {
                            Spot spot = new Spot();
                            spot.setCode("A" + j);
                            spot.setEtage(etage);
                            spot.setOccupied(new Random().nextBoolean());
                            spot.setType(ParkingSpotType.VIP);
                            spots.add(spot);
                        }
                    }

                    spotRepository.saveAll(spots);
                }
//            }
        };
    }
}

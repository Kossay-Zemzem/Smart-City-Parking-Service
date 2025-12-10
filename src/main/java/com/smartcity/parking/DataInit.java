package com.smartcity.parking;

import com.smartcity.parking.models.*;
import com.smartcity.parking.repository.EtageRepository;
import com.smartcity.parking.repository.EtageRepository;
import com.smartcity.parking.repository.ReservationRepository;
import com.smartcity.parking.repository.SpotRepository;
import com.smartcity.parking.services.SpotService;
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
    private final SpotService spotService;
    private final ReservationRepository reservationRepository;

    @Autowired
    public DataInit(EtageRepository etageRepository, SpotRepository spotRepository, ReservationRepository reservationRepository, SpotService spotService) {
        this.etageRepository = etageRepository;
        this.spotRepository = spotRepository;
        this.spotService = spotService;
        this.reservationRepository = reservationRepository;
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
                    etage.setHasPaymentKiosk(i % 2 == 0); // Payment kiosk on even floors
                    if (i == 0) {
                        etage.setCapacity(50); // Ground floor has higher capacity
                    } else if (i == 4) {
                        etage.setCapacity(30); // Top floor has lower capacity
                    } else {
                        etage.setCapacity(40); // Standard capacity for other floors
                    }
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

                        if (j == 5 && etage.getLabel().equals("F2")) {
                            //Ensure that the spot A5 on floor F2 is always free a test reservation
                            spot.setOccupied(false);
                        }
                        if (j == 10 && etage.getLabel().equals("F2")) {
                            //Ensure that the spot A10 on floor F2 is always free for testing
                            spot.setOccupied(false);
                        }
                        spot.setType(ParkingSpotType.VIP);
                        spots.add(spot);
                    }
                }

                spotRepository.saveAll(spots);

                //Add a n exising reservation for testing purposes
                Reservation reservation = new Reservation();
                reservation.setRefClient("CLIENT-007");
                //spot A5 on floor F2 is guranteed to be free for this test reservation
                reservation.setSpotCode("A5");
                reservation.setEtageLabel("F2");
                reservation.setDurationInHours(3);
                reservationRepository.save(reservation);
            }
        };
    }
}

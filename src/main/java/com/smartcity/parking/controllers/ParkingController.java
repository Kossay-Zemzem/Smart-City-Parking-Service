package com.smartcity.parking.controllers;

import com.smartcity.parking.models.Etage;
import com.smartcity.parking.models.Spot;
import com.smartcity.parking.services.EtageService;
import com.smartcity.parking.services.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import com.smartcity.parking.services.ParkingService;

@Controller
public class ParkingController {
    private final ParkingService parkingService;
    private final EtageService etageService;
    private final SpotService spotService;

    @Autowired
    public ParkingController(ParkingService parkingService, SpotService spotService, EtageService etageService) {
        this.etageService = etageService;
        this.spotService = spotService;
        this.parkingService = parkingService;
    }

    @QueryMapping
    public Spot spotById(@Argument Long id){
        return spotService.getSpotById(id);
    }

    @QueryMapping
    public Etage etageById(@Argument Long id){
        return etageService.getEtageById(id);
    }

}


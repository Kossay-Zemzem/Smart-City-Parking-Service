package com.smartcity.parking.controllers;

import com.smartcity.parking.models.Etage;
import com.smartcity.parking.models.Facture;
import com.smartcity.parking.models.Reservation;
import com.smartcity.parking.models.Spot;
import com.smartcity.parking.services.EtageService;
import com.smartcity.parking.services.ReservationService;
import com.smartcity.parking.services.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.smartcity.parking.services.ParkingService;

import javax.swing.plaf.PanelUI;
import java.util.List;

@Controller
public class ParkingController { //For now this controller will serve everything.
    private final ParkingService parkingService;
    private final ReservationService reservationService;
    private final EtageService etageService;
    private final SpotService spotService;

    @Autowired
    public ParkingController(ParkingService parkingService, ReservationService reservationService, SpotService spotService, EtageService etageService) {
        this.reservationService = reservationService;
        this.etageService = etageService;
        this.spotService = spotService;
        this.parkingService = parkingService;
    }

    @QueryMapping
    public Spot spotById(@Argument Long id){
        return spotService.getSpotById(id);
    }

    @QueryMapping
    public Spot spotByCode(@Argument("codeSpot") String codeSpot, @Argument("labelEtage") String labelEtage){
        return spotService.findSpot(codeSpot,labelEtage);
    }


    @QueryMapping
    public Etage etageById(@Argument Long id){
        return etageService.getEtageById(id);
    }

    @QueryMapping
    public Etage EtageByLabel(@Argument String labelEtage){
        return etageService.getEtageByLabel(labelEtage);
    }

    @QueryMapping
    public List<Etage> getAllEtages(){
        return etageService.getAllEtages();
    }

    @QueryMapping
    public List<Etage> emptySpots(){
        return etageService.getEtagesWithEmptySpots();
    }

//    @QueryMapping
//    public Spot closestEmptySpot(){
//        return parkingService.getClosestEmptySpot();
//    }

    @QueryMapping
    public List<Reservation> getReservationsByRefClient(@Argument String refClient){
        return reservationService.getReservationsByRefClient(refClient);
    }

    @QueryMapping
    public List<Reservation> getAllResrervations(){
        return reservationService.getAllReservations();
    }

    @MutationMapping
    public Facture reserveSpot(@Argument Reservation Reservation){
        return parkingService.reserveSpot(Reservation);
    }




}


package com.smartcity.parking.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Facture are only generated to be returned to the orchestrator, they will be stored in a different microservice

@Getter
@Setter
@NoArgsConstructor
public class Facture {
    private String refFacture;
    private String refClient;
    private Spot spot;
    private String etageLabel;
    private Integer durationInHours;
    private Double prixTTC;
}

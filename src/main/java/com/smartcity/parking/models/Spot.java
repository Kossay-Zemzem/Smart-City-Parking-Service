package com.smartcity.parking.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String code; // e.g., "A1", "B2"
    @Enumerated(EnumType.STRING)
    private ParkingSpotType type; // e.g., "normal", "VIP"
    private boolean isOccupied;

    @ManyToOne
    private Etage etage;
}

package com.smartcity.parking.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Etage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String label; // e.g., F0, F1, F2 (floor 0 = ground floor)
    private int capacity;
    private boolean hasPaymentKiosk; //vrai si il y a une borne de paiement à cet étage

    @OneToMany(mappedBy = "etage", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Spot> spots;

}

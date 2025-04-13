package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DistributionCentre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "distributionCentre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stock> stockEntries = new ArrayList<>();
}

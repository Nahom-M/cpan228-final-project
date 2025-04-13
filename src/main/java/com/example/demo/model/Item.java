package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Brand brand;

    private double price;
    private int yearCreated;
    private int quantity;

    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "distribution_centre_id")
    @JsonBackReference
    private DistributionCentre distributionCentre;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date(System.currentTimeMillis());
    }
}

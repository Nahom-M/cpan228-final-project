package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stock> stockEntries = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = new Date(System.currentTimeMillis());
    }
}

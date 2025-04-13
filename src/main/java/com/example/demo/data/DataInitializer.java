package com.example.demo.data;

import com.example.demo.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final DistributionCentreRepository centreRepo;
    private final ItemRepository itemRepo;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            DistributionCentre torontoCentre = new DistributionCentre();
            torontoCentre.setName("Toronto Distribution Centre");
            torontoCentre.setLatitude(43.651070);
            torontoCentre.setLongitude(-79.347015);
            centreRepo.save(torontoCentre);

            DistributionCentre vancouverCentre = new DistributionCentre();
            vancouverCentre.setName("Vancouver Distribution Centre");
            vancouverCentre.setLatitude(49.282729);
            vancouverCentre.setLongitude(-123.120738);
            centreRepo.save(vancouverCentre);

            Item item1 = new Item();
            item1.setName("Sports Jacket");
            item1.setBrand(Brand.GUCCI);
            item1.setPrice(79.99);
            item1.setYearCreated(2023);
            item1.setQuantity(25);
            item1.setCreatedAt(new Date(System.currentTimeMillis()));
            item1.setDistributionCentre(torontoCentre);

            Item item2 = new Item();
            item2.setName("Running Shoes");
            item2.setBrand(Brand.LOUIS_VUITTON);
            item2.setPrice(59.99);
            item2.setYearCreated(2022);
            item2.setQuantity(40);
            item2.setCreatedAt(new Date(System.currentTimeMillis()));
            item2.setDistributionCentre(torontoCentre);

            Item item3 = new Item();
            item3.setName("Tracksuit");
            item3.setBrand(Brand.PRADA);
            item3.setPrice(89.99);
            item3.setYearCreated(2024);
            item3.setQuantity(30);
            item3.setCreatedAt(new Date(System.currentTimeMillis()));
            item3.setDistributionCentre(vancouverCentre);

            itemRepo.saveAll(Arrays.asList(item1, item2, item3));
        };
    }
}

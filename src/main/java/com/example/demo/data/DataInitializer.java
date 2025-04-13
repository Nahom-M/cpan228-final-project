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

            DistributionCentre montrealCentre = new DistributionCentre();
            montrealCentre.setName("Montreal Distribution Centre");
            montrealCentre.setLatitude(897.288929);
            montrealCentre.setLongitude(-723.176738);
            centreRepo.save(montrealCentre);

            DistributionCentre ottawaCentre = new DistributionCentre();
            ottawaCentre.setName("Ottawa Distribution Centre");
            ottawaCentre.setLatitude(98.246754);
            ottawaCentre.setLongitude(-3.320918);
            centreRepo.save(ottawaCentre);

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

            Item item4 = new Item();
            item4.setName("Socks");
            item4.setBrand(Brand.LOUIS_VUITTON);
            item4.setPrice(109.79);
            item4.setYearCreated(2022);
            item4.setQuantity(10);
            item4.setCreatedAt(new Date(System.currentTimeMillis()));
            item4.setDistributionCentre(montrealCentre);

            Item item5 = new Item();
            item5.setName("Bracelet");
            item5.setBrand(Brand.VERSACE);
            item5.setPrice(29.50);
            item5.setYearCreated(2023);
            item5.setQuantity(17);
            item5.setCreatedAt(new Date(System.currentTimeMillis()));
            item5.setDistributionCentre(ottawaCentre);

            itemRepo.saveAll(Arrays.asList(item1, item2, item3, item4, item5));
        };
    }
}

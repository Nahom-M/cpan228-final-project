package com.example.demo.data;

import com.example.demo.model.*;
import com.example.demo.repository.*;
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
    private final StockRepository stockRepo;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Create distribution centres
            DistributionCentre toronto = new DistributionCentre(null, "Toronto", 43.65, -79.34, null);
            DistributionCentre vancouver = new DistributionCentre(null, "Vancouver", 49.28, -123.12, null);
            DistributionCentre montreal = new DistributionCentre(null, "Montreal", 45.50, -73.56, null);
            DistributionCentre ottawa = new DistributionCentre(null, "Ottawa", 45.42, -75.69, null);

            centreRepo.saveAll(Arrays.asList(toronto, vancouver, montreal, ottawa));

            // Create items
            Item jacket = new Item(null, "Sports Jacket", Brand.GUCCI, 79.99, 2023,
                    new Date(System.currentTimeMillis()), null);
            Item shoes = new Item(null, "Running Shoes", Brand.LOUIS_VUITTON, 59.99, 2022,
                    new Date(System.currentTimeMillis()), null);
            Item tracksuit = new Item(null, "Tracksuit", Brand.PRADA, 89.99, 2024, new Date(System.currentTimeMillis()),
                    null);
            Item socks = new Item(null, "Socks", Brand.LOUIS_VUITTON, 109.79, 2022,
                    new Date(System.currentTimeMillis()), null);
            Item bracelet = new Item(null, "Bracelet", Brand.VERSACE, 29.50, 2023, new Date(System.currentTimeMillis()),
                    null);

            itemRepo.saveAll(Arrays.asList(jacket, shoes, tracksuit, socks, bracelet));

            // Create stock entries (same item in multiple centres)
            Stock s1 = new Stock(null, 25, jacket, toronto);
            Stock s2 = new Stock(null, 10, jacket, vancouver);
            Stock s3 = new Stock(null, 40, shoes, toronto);
            Stock s4 = new Stock(null, 30, tracksuit, vancouver);
            Stock s5 = new Stock(null, 10, socks, montreal);
            Stock s6 = new Stock(null, 17, bracelet, ottawa);

            stockRepo.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6));
        };
    }
}

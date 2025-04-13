package com.example.demo.controller;

import com.example.demo.data.DistributionCentreRepository;
import com.example.demo.data.ItemRepository;
import com.example.demo.data.StockRepository;
import com.example.demo.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/distribution-centres")
@RequiredArgsConstructor
public class DistributionCentreController {

        private final DistributionCentreRepository centreRepo;
        private final ItemRepository itemRepo;
        private final StockRepository stockRepo;

        // Add stock of an existing item to a centre
        @PostMapping("/{centreId}/items")
        public ResponseEntity<Stock> addNewItemToCentre(
                        @PathVariable Long centreId,
                        @RequestBody Item item,
                        @RequestParam int quantity) {

                DistributionCentre centre = centreRepo.findById(centreId)
                                .orElseThrow(() -> new RuntimeException("Centre not found"));

                // Save the new item first
                Item savedItem = itemRepo.save(item);

                Stock stock = new Stock();
                stock.setDistributionCentre(centre);
                stock.setItem(savedItem);
                stock.setQuantity(quantity);

                // Maintain bidirectional integrity
                centre.getStockEntries().add(stock);
                savedItem.getStockEntries().add(stock);

                return ResponseEntity.ok(stockRepo.save(stock));
        }

        // Remove a stock entry (centre-item relation)
        @DeleteMapping("/stock/{stockId}")
        public ResponseEntity<Void> deleteStock(@PathVariable Long stockId) {
                Stock stock = stockRepo.findById(stockId)
                                .orElseThrow(() -> new RuntimeException("Stock not found"));

                // Remove stock from associated item and centre
                stock.getItem().getStockEntries().remove(stock);
                stock.getDistributionCentre().getStockEntries().remove(stock);

                // Then delete the stock
                stockRepo.delete(stock);

                return ResponseEntity.noContent().build();
        }

        // Get all centres
        @GetMapping
        public List<DistributionCentre> getAllCentres() {
                return centreRepo.findAll();
        }

        // Find all stock entries for a given item name and brand
        @GetMapping("/items/search")
        public List<Stock> findItemInCentres(
                        @RequestParam Brand brand,
                        @RequestParam String name) {

                return stockRepo.findAll().stream()
                                .filter(s -> s.getItem().getBrand() == brand &&
                                                s.getItem().getName().equalsIgnoreCase(name))
                                .toList();
        }
}

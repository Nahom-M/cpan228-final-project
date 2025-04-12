package com.example.demo.controller;

import com.example.demo.model.Brand;
import com.example.demo.model.DistributionCentre;
import com.example.demo.model.Item;
import com.example.demo.data.DistributionCentreRepository;
import com.example.demo.data.ItemRepository;
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

    @PostMapping("/{centreId}/items")
    public ResponseEntity<Item> addItem(@PathVariable Long centreId, @RequestBody Item item) {
        DistributionCentre centre = centreRepo.findById(centreId)
                .orElseThrow(() -> new RuntimeException("Centre not found"));
        item.setDistributionCentre(centre);
        return ResponseEntity.ok(itemRepo.save(item));
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
        itemRepo.deleteById(itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<DistributionCentre> getAllCentres() {
        return centreRepo.findAll();
    }

    @GetMapping("/items/search")
    public List<Item> requestItem(
            @RequestParam Brand brand,
            @RequestParam String name) {
        return itemRepo.findByBrandAndName(brand, name);
    }
}

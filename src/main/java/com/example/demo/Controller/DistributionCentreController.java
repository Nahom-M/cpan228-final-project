package com.example.demo.controller;

import com.example.demo.model.Brand;
import com.example.demo.model.DistributionCentre;
import com.example.demo.model.Item;
import com.example.demo.data.DistributionCentreRepository;
import com.example.demo.data.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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

    @PostMapping("/request-item")
    public String requestItemFromCentres(@RequestParam String name,
            @RequestParam Brand brand,
            Model model) {
        List<Item> items = itemRepo.findByBrandAndName(brand, name);

        if (items.isEmpty()) {
            model.addAttribute("message", "Item not found in any distribution centre.");
            return "error";
        }

        Item sourceItem = items.stream()
                .filter(i -> i.getQuantity() > 0)
                .findFirst()
                .orElse(null);

        if (sourceItem == null) {
            model.addAttribute("message", "Item found, but all centres are out of stock.");
            return "error";
        }

        // Reduce quantity at distribution centre
        sourceItem.setQuantity(sourceItem.getQuantity() - 1);
        itemRepo.save(sourceItem);

        // Simulate replenishing to warehouse (could be another repo/entity)
        // For now, just display success page
        model.addAttribute("item", sourceItem);
        return "success";
    }

}

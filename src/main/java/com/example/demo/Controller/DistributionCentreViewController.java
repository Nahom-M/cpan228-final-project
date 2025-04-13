package com.example.demo.controller;

import com.example.demo.data.StockRepository;
import com.example.demo.model.Brand;
import com.example.demo.model.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DistributionCentreViewController {

    private final StockRepository stockRepo;

    // Show the form page
    @GetMapping("/request-item")
    public String showRequestForm() {
        return "request-item";
    }

    // Handle form submission
    @PostMapping("/distribution-centres/request-item")
    public String requestItemFromAnyCentre(@RequestParam String name,
            @RequestParam Brand brand,
            Model model) {
        List<Stock> stockList = stockRepo.findAll().stream()
                .filter(s -> s.getItem().getName().equalsIgnoreCase(name) &&
                        s.getItem().getBrand() == brand &&
                        s.getQuantity() > 0)
                .toList();

        if (stockList.isEmpty()) {
            model.addAttribute("message", "Item not found or out of stock in all centres.");
            return "error";
        }

        Stock stockToUpdate = stockList.get(0);
        stockToUpdate.setQuantity(stockToUpdate.getQuantity() - 1);
        stockRepo.save(stockToUpdate);

        model.addAttribute("item", stockToUpdate.getItem());
        model.addAttribute("centre", stockToUpdate.getDistributionCentre());
        return "success";
    }
}

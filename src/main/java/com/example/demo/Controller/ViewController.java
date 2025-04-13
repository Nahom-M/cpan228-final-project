package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.data.DistributionCentreRepository;

@Controller
public class ViewController {

    @Autowired
    private DistributionCentreRepository centreRepo;

    @GetMapping("/centres")
    public String showCentres(Model model) {
        model.addAttribute("centres", centreRepo.findAll());
        return "centres";
    }

    @GetMapping("/add-item")
    public String showAddItemForm() {
        return "addItem";
    }

    @GetMapping("/search-item")
    public String showSearchForm() {
        return "searchItem";
    }
}

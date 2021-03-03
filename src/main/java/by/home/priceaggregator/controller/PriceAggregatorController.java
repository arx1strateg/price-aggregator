package by.home.priceaggregator.controller;

import by.home.priceaggregator.model.Item;
import by.home.priceaggregator.service.PriceAggregatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceAggregatorController {
    @Autowired
    private PriceAggregatorService priceAggregatorService;

    @GetMapping("/items")
    public List<Item> getItems() {
        return priceAggregatorService.getItems();
    }
}

package com.fruitcart.FruitCart.controller;

import com.fruitcart.FruitCart.entity.Product;
import com.fruitcart.FruitCart.service.PriceEngineService;
import com.fruitcart.FruitCart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/pricing")
@CrossOrigin
public class PriceEngineController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PriceEngineService priceEngineService;

    @GetMapping("/calculate")
    public double calculatePrice(@RequestParam String productName,
                                 @RequestParam int quantity){

        Product product = productService.getProductByName(productName);
        return priceEngineService.calculatePrice(product, quantity);

    }

    @GetMapping("/calculateUnitPrice")
    public double getUnitPrice(@RequestParam String name){
        Product product = productService.getProductByName(name);
        return priceEngineService.getUnitPrice(product);
    }


}

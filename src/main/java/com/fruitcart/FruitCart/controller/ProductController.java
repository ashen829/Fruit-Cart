package com.fruitcart.FruitCart.controller;

import com.fruitcart.FruitCart.entity.Product;
import com.fruitcart.FruitCart.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<String> saveProduct(
            @RequestParam("name") String name,
            @RequestParam("quantity") int quantity,
            @RequestParam("cartonPrice") double cartonPrice,
            @RequestParam("unitsPerCarton") int unitsPerCarton ,
            @RequestParam("description") String description,
            @RequestParam("unitPriceMarkup") int unitPriceMarkup,
            @RequestParam("cartonDiscountPercentage") int cartonDiscountPercentage,
            @RequestParam("image")MultipartFile image
            ){
        try {
            productService.saveProduct(name,quantity,cartonPrice,unitsPerCarton,description,unitPriceMarkup,cartonDiscountPercentage,image);
            return new ResponseEntity<>("Product Saved successfully!", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error saving product!",HttpStatus.INSUFFICIENT_STORAGE);
        }
    }

    @GetMapping("/getProducts")
    public List<Product>getAllProducts(){
        return productService.getAllProducts();
    }


    @GetMapping("/getProduct/{name}")
    public Product getProductByName(@PathVariable("name") String name){
        return productService.getProductByName(name);
    }
}

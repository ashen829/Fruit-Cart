package com.fruitcart.FruitCart.service;

import com.fruitcart.FruitCart.entity.Product;
import com.fruitcart.FruitCart.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(String name, int quantity, double cartonPrice, int unitsPerCarton, String description,int unitPriceMarkup,int cartonDiscountPercentage, MultipartFile imageFile) throws IOException {
        Product product = new Product();
        product.setName(name);
        product.setQuantity(quantity);
        product.setCartonPrice(cartonPrice);
        product.setUnitsPerCarton(unitsPerCarton);
        product.setDescription(description);
        product.setUnitPriceMarkup(unitPriceMarkup);
        product.setCartonDiscountPercentage(cartonDiscountPercentage);
        product.setImage(imageFile.getBytes());
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products;
    }


    public Product getProductByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Product not found with name: " + name));
    }


}



package com.fruitcart.FruitCart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Entity
public class Product {

    @Id
    String name;
    int quantity;
    double cartonPrice;
    int unitsPerCarton;
    String description;
    int unitPriceMarkup;
    int cartonDiscountPercentage;
    @Lob
    private byte[] image;

    public Product() {
    }

    public Product(String name, int quantity, double cartonPrice, int unitsPerCarton, String description, int unitPriceMarkup, int cartonDiscountPercentage, byte[] image) {
        this.name = name;
        this.quantity = quantity;
        this.cartonPrice = cartonPrice;
        this.unitsPerCarton = unitsPerCarton;
        this.description = description;
        this.unitPriceMarkup = unitPriceMarkup;
        this.cartonDiscountPercentage = cartonDiscountPercentage;
        this.image = image;
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCartonPrice() {
        return cartonPrice;
    }

    public void setCartonPrice(double cartonPrice) {
        this.cartonPrice = cartonPrice;
    }

    public int getUnitsPerCarton() {
        return unitsPerCarton;
    }

    public void setUnitsPerCarton(int unitsPerCarton) {
        this.unitsPerCarton = unitsPerCarton;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUnitPriceMarkup() {
        return unitPriceMarkup;
    }

    public void setUnitPriceMarkup(int unitPriceMarkup) {
        this.unitPriceMarkup = unitPriceMarkup;
    }

    public int getCartonDiscountPercentage() {
        return cartonDiscountPercentage;
    }

    public void setCartonDiscountPercentage(int cartonDiscountPercentage) {
        this.cartonDiscountPercentage = cartonDiscountPercentage;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}

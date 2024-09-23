package com.fruitcart.FruitCart.service;

import com.fruitcart.FruitCart.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PriceEngineService {

    public double calculatePrice(Product product, int units){
        int cartons = units/ product.getUnitsPerCarton();
        int singleUnits = units % product.getUnitsPerCarton();
        double singleUnitPrice = product.getCartonPrice() / product.getUnitsPerCarton();
        double unitPriceWithMarkup =singleUnitPrice * ((product.getUnitPriceMarkup()/100.0)+1);

        double totalPrice = cartons * product.getCartonPrice();
        if(cartons>=2){
            totalPrice *= 0.8;
        }
        totalPrice += singleUnits * unitPriceWithMarkup;
        System.out.println(cartons);
        System.out.println(singleUnits);
        System.out.println(singleUnitPrice);
        System.out.println(unitPriceWithMarkup);
        return totalPrice;
    }

    public double getUnitPrice(Product product){
        double singleUnitPrice = product.getCartonPrice() / product.getUnitsPerCarton();
        double unitPriceWithMarkup =singleUnitPrice * ((product.getUnitPriceMarkup()/100.0)+1);
        return unitPriceWithMarkup;
    }
}

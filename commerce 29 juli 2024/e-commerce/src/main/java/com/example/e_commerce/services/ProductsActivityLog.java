package com.example.e_commerce.services;

import com.example.e_commerce.model.entities.CartsActivityLogEntity;
import com.example.e_commerce.model.entities.ProductsActivityLogEntity;

public interface ProductsActivityLog {
    void addLogProducts (ProductsActivityLogEntity productsActivityLogEntity);
    void updateLogProducts (ProductsActivityLogEntity productsActivityLogEntity);
}

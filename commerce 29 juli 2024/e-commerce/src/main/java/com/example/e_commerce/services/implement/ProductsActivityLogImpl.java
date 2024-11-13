package com.example.e_commerce.services.implement;

import com.example.e_commerce.model.entities.ProductsActivityLogEntity;
import com.example.e_commerce.repositories.ProductsLogRepository;
import com.example.e_commerce.services.ProductsActivityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsActivityLogImpl implements ProductsActivityLog {

    @Autowired
    ProductsLogRepository productsLogRepository;

    @Override
    public void addLogProducts(ProductsActivityLogEntity productsActivityLogEntity){
        productsLogRepository.save(productsActivityLogEntity);
    }

    @Override
    public void updateLogProducts(ProductsActivityLogEntity productsActivityLogEntity){
        productsLogRepository.save(productsActivityLogEntity);
    }
}

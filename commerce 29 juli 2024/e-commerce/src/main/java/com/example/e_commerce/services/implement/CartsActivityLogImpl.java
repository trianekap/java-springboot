package com.example.e_commerce.services.implement;

import com.example.e_commerce.model.entities.CartsActivityLogEntity;
import com.example.e_commerce.repositories.CartsLogRepository;
import com.example.e_commerce.services.CartsActivityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartsActivityLogImpl implements CartsActivityLog {
    @Autowired
    CartsLogRepository cartsLogRepository;

    @Override
    public void addLogCart(CartsActivityLogEntity cartsActivityLogEntity){
        cartsLogRepository.save(cartsActivityLogEntity);
    }

    @Override
    public void updateLogCart(CartsActivityLogEntity cartsActivityLogEntity){
        cartsLogRepository.save(cartsActivityLogEntity);
    }
}

package com.example.e_commerce.services;

import com.example.e_commerce.model.entities.CartsActivityLogEntity;

public interface CartsActivityLog {
    void addLogCart (CartsActivityLogEntity cartsActivityLogEntity);
    void updateLogCart (CartsActivityLogEntity cartsActivityLogEntity);
}

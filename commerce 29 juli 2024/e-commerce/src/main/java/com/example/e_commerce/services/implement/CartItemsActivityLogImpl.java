package com.example.e_commerce.services.implement;

import com.example.e_commerce.model.entities.CartItemsActivityLogEntity;
import com.example.e_commerce.repositories.CartItemsLogRepository;
import com.example.e_commerce.services.CartItemsActivityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemsActivityLogImpl implements CartItemsActivityLog {
    @Autowired
    CartItemsLogRepository cartItemsLogRepository;

    @Override
    public void addLogCartItems(CartItemsActivityLogEntity cartItemsActivityLogEntity){
        cartItemsLogRepository.save(cartItemsActivityLogEntity);
    }

    @Override
    public void updateLogCartItems(CartItemsActivityLogEntity cartItemsActivityLog){
        cartItemsLogRepository.save(cartItemsActivityLog);
    }
}

package com.example.e_commerce.services;

import com.example.e_commerce.model.entities.CartItemsActivityLogEntity;

public interface CartItemsActivityLog{

        void addLogCartItems(CartItemsActivityLogEntity cartItemsActivityLogEntity);

        void updateLogCartItems(CartItemsActivityLogEntity cartItemsActivityLog);
}

package com.example.e_commerce.services;

import com.example.e_commerce.model.entities.UsersActivityLogEntity;

public interface UsersActivityLog {
    void addLogUser (UsersActivityLogEntity usersActivityLogEntity);
    void updateLogUser (UsersActivityLogEntity usersActivityLogEntity);
}

package com.example.e_commerce.services.implement;

import com.example.e_commerce.model.entities.UsersActivityLogEntity;
import com.example.e_commerce.repositories.UsersLogRepository;
import com.example.e_commerce.services.UsersActivityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersActivityLogImpl implements UsersActivityLog {
    @Autowired
    UsersLogRepository usersLogRepository;

    @Override
    public void addLogUser(UsersActivityLogEntity usersActivityLogEntity){
        usersLogRepository.save(usersActivityLogEntity);
    }

    @Override
    public void updateLogUser(UsersActivityLogEntity usersActivityLogEntity){
        usersLogRepository.save(usersActivityLogEntity);
    }

//    @Override
//    public void deleteLogUser(UsersActivityLog usersActivityLogID){
//        usersLogRepository.delete(usersActivityLogID.deleteLogUser();
//    }

}

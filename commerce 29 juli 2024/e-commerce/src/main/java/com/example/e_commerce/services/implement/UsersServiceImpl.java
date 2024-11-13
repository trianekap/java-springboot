package com.example.e_commerce.services.implement;

import com.example.e_commerce.exceptions.ResourceInvalidConstraintViolation;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.UsersAddDTO;
import com.example.e_commerce.model.dtos.UsersGetDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.UsersResponseDTO;
import com.example.e_commerce.model.entities.Users;
import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.model.entities.UsersActivityLogEntity;
import com.example.e_commerce.repositories.UsersRepository;
import com.example.e_commerce.services.UsersActivityLog;
import com.example.e_commerce.services.UsersService;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersActivityLog usersActivityLog2;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Override
    public UsersGetDTO addUsers(UsersAddDTO usersAddDto) throws ResourceAlreadyExistException {

        Users usersConvert = convertFromDTO(usersAddDto);

        Users usersCheck = usersRepository.findUsersByMobileNumber(usersConvert.getMobileNumber());
        if (usersCheck != null){
            String message = "mobile number has been created in database";
            logger.error(message);
            throw new ResourceAlreadyExistException(message);
        }

        Users users = new Users();
        users.setEmail(usersAddDto.getEmail());
        users.setFirstName(usersAddDto.getFirstName());
        users.setLastName(usersAddDto.getLastName());
        users.setMobileNumber(usersAddDto.getMobileNumber());
        users.setPassword(usersAddDto.getPassword());
        users.setCreated_at(ZonedDateTime.now(ZoneId.of("Asia/Jakarta")));

        UsersActivityLogEntity usersActivityLogEntity = new UsersActivityLogEntity();
        usersActivityLogEntity.setUsers2(users);
        usersActivityLogEntity.setActivityDate(ZonedDateTime.now(ZoneId.of("Asia/Jakarta")));
        usersActivityLogEntity.setActivityType(UsersActivityLogEntity.ActivityType.CREATED_USER.getType());

        usersRepository.save(users);
        usersActivityLog2.addLogUser(usersActivityLogEntity);

        return convertToUsersGetDTO(users);

    }

    @Override
    public UsersResponseDTO getAllUsers(int page_number, int page_size, String sort_order, String sort_by){
        //sorting data
        Sort sortByAndOrders = sort_order.equalsIgnoreCase("ASC")? Sort.by(sort_by).ascending() :
                Sort.by(sort_by).descending();
        //create request
        Pageable detail = PageRequest.of(page_number, page_size, sortByAndOrders);
        //get data from repo
        Page<Users> usersPage = usersRepository.findAll(detail);
        //data to list
        List<Users> usersList = usersPage.getContent();
        //convert DTO
        List<UsersGetDTO> usersGetDTOList = usersList.stream()
                .map(u -> modelMapper.map(u, UsersGetDTO.class))
                .collect(Collectors.toList());
        //response
        UsersResponseDTO usersResponseDTO = new UsersResponseDTO();
        usersResponseDTO.setContent(usersGetDTOList);
        usersResponseDTO.setPage_number(usersPage.getNumber());
        usersResponseDTO.setPage_size(usersPage.getSize());
        usersResponseDTO.setTotal_element(usersPage.getTotalElements());
        usersResponseDTO.setTotal_page(usersPage.getTotalPages());
        usersResponseDTO.setLast_page(usersPage.isLast());

        return usersResponseDTO;
    }

    @Override
    public UsersGetDTO getUsersById(UUID user_id, UsersGetDTO usersGetDTO) throws ResourceNotExistException {

        Users existedUsers = getExistence(user_id);

        return convertToUsersGetDTO(existedUsers);
    }


    @Override
    public void updateUsersById(UUID user_id, UsersGetDTO usersGetDTO) throws ResourceNotExistException, ResourceAlreadyExistException, ResourceInvalidConstraintViolation {

        Users existedUser = getExistence(user_id);
        Users convertedUser = convertFromDTO(usersGetDTO);

        existedUser.setEmail(usersGetDTO.getEmail());
        existedUser.setFirstName(usersGetDTO.getFirstName());
        existedUser.setLastName(usersGetDTO.getLastName());
        existedUser.setMobileNumber(usersGetDTO.getMobileNumber());
        existedUser.setPassword(usersGetDTO.getPassword());
        existedUser.setModified_at(ZonedDateTime.now(ZoneId.of("Asia/Jakarta")));

        UsersActivityLogEntity usersActivityLogEntity = new UsersActivityLogEntity();
        usersActivityLogEntity.setUsers2(existedUser);
        usersActivityLogEntity.setActivityDate(ZonedDateTime.now(ZoneId.of("Asia/Jakarta")));
        usersActivityLogEntity.setActivityType(UsersActivityLogEntity.ActivityType.MODIFIED_USER.getType());

        usersRepository.save(existedUser);
        usersActivityLog2.updateLogUser(usersActivityLogEntity);
    }

    @Override
    public void deleteUsersById(UUID user_id) throws ResourceNotExistException{

        Users existedUser = getExistence(user_id);
        usersRepository.delete(existedUser);

    }

    @Override
    public List<UsersGetDTO> getUserByKeyword(String keyword) throws ResourceNotExistException {
        List<Users> checkUser = usersRepository.findUsersByKeyword(keyword);
        if (checkUser.isEmpty()) {
            String errorMessage = "Resource with that keyword " + keyword + "doesn't exist";
            logger.error(errorMessage);
            throw new ResourceNotExistException(errorMessage);
        }

        return checkUser.stream().map(this::convertToUsersGetDTO).collect(Collectors.toList());
    }


    private Users getExistence(UUID userId) throws ResourceNotExistException {
        Users existedUser = usersRepository.findUsersById(userId);
        if (existedUser == null){
            String errorMessage = "Resource with user id " + userId + " doesn't exist!";
            logger.error(errorMessage);
            throw new ResourceNotExistException(errorMessage);
        }

        return existedUser;
    }

    /* cara convert DTO dengan menggunakan library model mapper
    private UsersGetDTO convertToUsersGetDTO(Users users){
        return modelMapper.map(users, UsersGetDTO.class);
    }

    private UsersAddDTO convertToUsersAddDTO(Users users){
        return modelMapper.map(users, UsersAddDTO.class);
    }
    */


    private Users convertFromDTO(UsersAddDTO usersAddDTO){
        return modelMapper.map(usersAddDTO, Users.class);
    }
    private Users convertFromDTO(UsersGetDTO usersGetDTO){
        return modelMapper.map(usersGetDTO, Users.class);
    }

    //cara convert dengan manual
    public UsersAddDTO convertToUsersAddDTO(Users users){
//        if (users == null){
//            return null;
//        }
        UsersAddDTO dto = new UsersAddDTO();
        dto.setFirstName(users.getFirstName());
        dto.setLastName(users.getLastName());
        dto.setEmail(users.getEmail());
        dto.setMobileNumber(users.getMobileNumber());
        dto.setPassword(users.getPassword());
        return dto;
    }


    public UsersGetDTO convertToUsersGetDTO(Users users) {
//        if (users == null) {
//            return null;
//        }
        UsersGetDTO dto = new UsersGetDTO();
        dto.setFirstName(users.getFirstName());
        dto.setLastName(users.getLastName());
        dto.setEmail(users.getEmail());
        dto.setMobileNumber(users.getMobileNumber());
        dto.setPassword(users.getPassword());
        return dto;
    }

}

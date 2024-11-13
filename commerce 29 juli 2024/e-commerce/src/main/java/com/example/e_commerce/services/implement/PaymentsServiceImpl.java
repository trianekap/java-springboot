package com.example.e_commerce.services.implement;

import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.PaymentsAddDTO;
import com.example.e_commerce.model.dtos.PaymentsGetDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.PaymentsResponseDTO;
import com.example.e_commerce.model.entities.Payments;
import com.example.e_commerce.repositories.PaymentsRepository;
import com.example.e_commerce.services.PaymentsService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    PaymentsRepository paymentsRepository;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(PaymentsServiceImpl.class);

    @Override
    public PaymentsGetDTO addPayments(PaymentsAddDTO paymentsAddDTO) throws ResourceAlreadyExistException{

        Payments convertPay = convertFromDto(paymentsAddDTO);
        Payments checkPayment = paymentsRepository.findPaymentsByName(paymentsAddDTO.getPaymentMethod());
        if (checkPayment != null){
            throw new ResourceAlreadyExistException("resource already exist");
        }

        Payments payments2 = new Payments();
        payments2.setPaymentMethod(paymentsAddDTO.getPaymentMethod());

        paymentsRepository.save(payments2);

        return convertToDto(payments2);
    }

    @Override
    public PaymentsGetDTO getPaymentsById(UUID payment_id, PaymentsGetDTO paymentsGetDTO) throws ResourceNotExistException {

        Payments payments1 = paymentsRepository.findPaymentsById(payment_id);
        if (payments1 == null){
            throw new ResourceNotExistException("payments with that id not exist");
        }

        return convertToDto(payments1);
    }

    @Override
    public PaymentsResponseDTO getAllPaymentsPagination(int page_number, int page_size, String sort_order, String sort_by){
        //sorting data
        Sort sortByAndOrders = sort_order.equalsIgnoreCase("ASC")? Sort.by(sort_by).ascending() :
                Sort.by(sort_by).descending();
        //create request
        Pageable detail = PageRequest.of(page_number, page_size, sortByAndOrders);
        //get data from repo
        Page<Payments> paymentsPage = paymentsRepository.findAll(detail);
        //data to list
        List<Payments> paymentsList = paymentsPage.getContent();
        //convert DTO
        List<PaymentsGetDTO> paymentsGetDTOList = paymentsList.stream().map(u -> modelMapper.map(u, PaymentsGetDTO.class)
        ).collect(Collectors.toList());
        //response
        PaymentsResponseDTO paymentsResponseDTO = new PaymentsResponseDTO();
        paymentsResponseDTO.setContent(paymentsGetDTOList);
        paymentsResponseDTO.setPage_number(paymentsPage.getNumber());
        paymentsResponseDTO.setPage_size(paymentsPage.getSize());
        paymentsResponseDTO.setTotal_element(paymentsPage.getTotalElements());
        paymentsResponseDTO.setLast_page(paymentsPage.isLast());

        return paymentsResponseDTO;
    }

    @Override
    public void updatePaymentsById(UUID payment_id, PaymentsAddDTO paymentsAddDTO) throws ResourceNotExistException, ResourceAlreadyExistException{

        Payments checkPayment = paymentsRepository.findPaymentsById(payment_id);
        if (checkPayment == null){
            throw new ResourceNotExistException("payments with that id not existed");
        }

        checkPayment.setPaymentMethod(paymentsAddDTO.getPaymentMethod());

        paymentsRepository.save(checkPayment);

    }

    @Override
    public void deletePaymentsById(UUID payment_id) throws ResourceNotExistException{

        Payments payment1 = paymentsRepository.findPaymentsById(payment_id);
        if (payment1 == null){
            throw new ResourceNotExistException("payment with that id not existed");
        }

        paymentsRepository.delete(payment1);
    }

    private Payments convertFromDto(PaymentsAddDTO paymentsAddDTO){
        return modelMapper.map(paymentsAddDTO, Payments.class);
    }

    private PaymentsGetDTO convertToDto(Payments payments){
        return modelMapper.map(payments, PaymentsGetDTO.class);
    }

}

package com.example.e_commerce.services;

import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.PaymentsAddDTO;
import com.example.e_commerce.model.dtos.PaymentsGetDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.PaymentsResponseDTO;

import java.util.UUID;

public interface PaymentsService {
    PaymentsGetDTO addPayments(PaymentsAddDTO paymentsAddDTO) throws ResourceAlreadyExistException;

    PaymentsResponseDTO getAllPaymentsPagination(int page_number, int page_size, String sort_order, String sort_by);

    PaymentsGetDTO getPaymentsById(UUID payment_id, PaymentsGetDTO paymentsGetDTO) throws ResourceNotExistException;

    void updatePaymentsById(UUID payments_id, PaymentsAddDTO paymentsAddDTO) throws ResourceNotExistException, ResourceAlreadyExistException;

    void deletePaymentsById(UUID payments_id) throws ResourceNotExistException;
}

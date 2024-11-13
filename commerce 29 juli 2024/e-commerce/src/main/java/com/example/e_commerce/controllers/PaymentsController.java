package com.example.e_commerce.controllers;


import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceInvalidConstraintViolation;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.PaymentsAddDTO;
import com.example.e_commerce.model.dtos.PaymentsGetDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.PaymentsResponseDTO;
import com.example.e_commerce.model.responses.ApiResponses;
import com.example.e_commerce.model.responses.ResponseMessages;
import com.example.e_commerce.services.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class PaymentsController {

    @Autowired
    PaymentsService paymentsService;

    @PostMapping("/addpayment")
    public ApiResponses<Object> addPayments(@RequestBody PaymentsAddDTO paymentsAddDTO) throws ResourceAlreadyExistException {

        PaymentsGetDTO payments1 = paymentsService.addPayments(paymentsAddDTO);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_CREATED.getMessages(), null);
    }

    @GetMapping("/getallpaymentspagination")
    public ResponseEntity<PaymentsResponseDTO> getPaymentsByPagination(
            @RequestParam int page_number,
            @RequestParam int page_size,
            @RequestParam String sort_order,
            @RequestParam(defaultValue = "paymentId") String sort_by
    ){

        PaymentsResponseDTO paymentsResponseDTO = paymentsService.getAllPaymentsPagination(page_number, page_size, sort_order, sort_by);

        return new ResponseEntity<PaymentsResponseDTO>(paymentsResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/getpaymentbyid/{payment_id}")
    public ApiResponses<Object> getPaymentsById(@PathVariable UUID payment_id, PaymentsGetDTO paymentsGetDTO) throws ResourceNotExistException {

        PaymentsGetDTO payments1 = paymentsService.getPaymentsById(payment_id, paymentsGetDTO);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_CREATED.getMessages(), payments1);
    }

    @PutMapping("/updatepaymentsbyid/{payment_id}")
    public ApiResponses<Object> updatePaymentsById(@PathVariable UUID payment_id, @RequestBody PaymentsAddDTO paymentsAddDTO) throws ResourceNotExistException, ResourceAlreadyExistException, ResourceInvalidConstraintViolation {

        paymentsService.updatePaymentsById(payment_id, paymentsAddDTO);

        return ApiResponses.success(HttpStatus.ACCEPTED, ResponseMessages.RESOURCE_MODIFIED.getMessages(), null);
    }

    @DeleteMapping("/deletepaymentsbyid/{payment_id}")
    public ApiResponses<Object> deletePaymentsById(@PathVariable UUID payment_id) throws ResourceNotExistException {

        paymentsService.deletePaymentsById(payment_id);
        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_DELETED.getMessages(), null);
    }
}

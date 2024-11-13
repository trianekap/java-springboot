package com.codean.topup.models.responses;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse<T>{
    private int statusCode;
    private String status;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(HttpStatus httpStatus, String message, T data){
        ApiResponse<T> responseApi = new ApiResponse<>();
        responseApi.setStatusCode(httpStatus.value());
        responseApi.setStatus(httpStatus.getReasonPhrase());
        responseApi.setMessage(message);
        responseApi.setData(data);

        return responseApi;

    }

    public static <T> ApiResponse<T> fail(HttpStatus httpStatus, String message, T error){
        ApiResponse<T> responseApi = new ApiResponse<>();
        responseApi.setStatusCode(httpStatus.value());
        responseApi.setStatus(httpStatus.getReasonPhrase());
        responseApi.setMessage(message);
        responseApi.setData(error);

        return responseApi;

    }

    public static <T> ApiResponse<T> fail(HttpStatus httpStatus, String message){
        ApiResponse<T> responseApi = new ApiResponse<>();
        responseApi.setStatusCode(httpStatus.value());
        responseApi.setStatus(httpStatus.getReasonPhrase());
        responseApi.setMessage(message);

        return responseApi;

    }
}

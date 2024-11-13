package com.codean.mybatispesantren.model.responses;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponses <T>{
    private int statusCode;
    private String status;
    private String message;
    private T data;
    private T errors;

    public static <T> ApiResponses<T> success(HttpStatus httpStatus, String message, T data){
        ApiResponses<T> responseApi = new ApiResponses<>();
        responseApi.setStatusCode(httpStatus.value());
        responseApi.setStatus(httpStatus.getReasonPhrase());
        responseApi.setMessage(message);
        responseApi.setData(data);

        return responseApi;

    }

    public static <T> ApiResponses<T> fail(HttpStatus httpStatus, String message, T error){
        ApiResponses<T> responseApi = new ApiResponses<>();
        responseApi.setStatusCode(httpStatus.value());
        responseApi.setStatus(httpStatus.getReasonPhrase());
        responseApi.setMessage(message);
        responseApi.setData(error);

        return responseApi;

    }

    public static <T> ApiResponses<T> fail(HttpStatus httpStatus, String message){
        ApiResponses<T> responseApi = new ApiResponses<>();
        responseApi.setStatusCode(httpStatus.value());
        responseApi.setStatus(httpStatus.getReasonPhrase());
        responseApi.setMessage(message);

        return responseApi;

    }
}


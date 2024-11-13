package com.codean.CoffeShop.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessages {
    RESOURCE_CREATED("resource successfully created"),
    RESOURCE_FETCHED("resource successfully fetched"),
    RESOURCE_MODIFIED("resource successfully modified"),
    RESOURCE_DELETED("resource successfully deleted"),
    RESOURCE_NOTFOUND("resource not found"),
    RESOURCE_ALREADY_EXIST("resource already exist!"),
    RESOURCE_CONFLICT("resource has been created in database"),
    BAD_REQUEST("bad request");

    private final String messages;

}

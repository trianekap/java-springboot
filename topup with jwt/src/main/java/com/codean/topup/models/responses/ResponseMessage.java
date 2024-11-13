package com.codean.topup.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    RESOURCE_CREATED("resource succesfully created"),
    RESOURCE_FETCHED("resource succesfully fetched"),
    RESOURCE_MODIFIED("resource succesfully modified"),
    RESOURCE_DELETED("resource succesfully deleted"),
    RESOURCE_NOTFOUND("resource not found"),
    RESOURCE_ALREADY_EXIST("resource already exist"),
    RESOURCE_CONFLICT("resource has been created in database"),
    BAD_REQUEST("bad request"),
    EMAIL_NOTFOUND("email not found");

    private final String messages;

}

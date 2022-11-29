package com.compay.msbanking.enums;

public enum ErrorEnum {
    CUSTOMER_NOT_FOUND("Customer not found",404),
    ACCOUNT_NOT_FOUND("Account not found",404),
    CARD_NOT_FOUND("Card not found",404),
    INVALID_REQUEST("Invalid request",400),
    CUSTOMER_ALREADY_EXIST("Customer already exist",403);



    private final String message;
    private final Integer code;


    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    ErrorEnum(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}

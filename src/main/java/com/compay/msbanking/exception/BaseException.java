package com.compay.msbanking.exception;

import com.compay.msbanking.enums.ErrorEnum;

public class BaseException extends RuntimeException{

    private int code;
    private String message;

    public static BaseException of(ErrorEnum errorEnum){
        return new BaseException()
                .setCode(errorEnum.getCode())
                .setMessage(errorEnum.getMessage());
    }



    public int getCode() {
        return code;
    }

    public BaseException setCode(int code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BaseException setMessage(String message) {
        this.message = message;
        return this;
    }
}

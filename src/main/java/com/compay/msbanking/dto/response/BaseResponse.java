package com.compay.msbanking.dto.response;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private String message;
    private Integer code;
    private  T data;



    public BaseResponse() {
    }

    public BaseResponse(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public BaseResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public BaseResponse<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public BaseResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public BaseResponse<T> success(T data){
        return new BaseResponse<T>()
                .setData(data)
                .setCode(200)
                .setMessage("Success");
    }




}

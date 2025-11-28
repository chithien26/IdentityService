package com.demo.salesapp.exception;

import lombok.Setter;

public enum ErrorCode {
    INVALID_AGE(100, "Age must be between 18 and 65"),
    DUPLICATE_USERNAME(101, "Username already exists");



    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public  int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}

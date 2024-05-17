package com.example.LibraryManagement.exception.base;


import static com.example.LibraryManagement.constant.ExceptionCode.BAD_REQUEST_CODE;
import static com.example.LibraryManagement.exception.base.StatusConstants.BAD_REQUEST;

public class BadRequestException extends BaseException{
    public BadRequestException() {
        setCode(BAD_REQUEST_CODE);
        setStatus(BAD_REQUEST);
    }


}

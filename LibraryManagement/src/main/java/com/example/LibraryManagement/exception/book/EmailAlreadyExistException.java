package com.example.LibraryManagement.exception.book;

import com.example.LibraryManagement.exception.base.BadRequestException;

import static com.example.LibraryManagement.constant.ExceptionCode.READER_ALREADY_EXIST_EXCEPTION;

public class EmailAlreadyExistException extends BadRequestException {
    public EmailAlreadyExistException() {
        setCode("com.example.LibraryManagement.exception.book.EmailAlreadyExistException");
    }
}

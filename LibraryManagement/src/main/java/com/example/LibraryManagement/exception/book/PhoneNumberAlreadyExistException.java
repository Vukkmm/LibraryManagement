package com.example.LibraryManagement.exception.book;

import com.example.LibraryManagement.exception.base.BadRequestException;

public class PhoneNumberAlreadyExistException extends BadRequestException {
    public PhoneNumberAlreadyExistException() {
        setCode("com.example.LibraryManagement.exception.book.PhoneNumberAlreadyExistException");
    }
}

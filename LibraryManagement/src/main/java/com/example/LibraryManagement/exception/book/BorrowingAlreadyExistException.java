package com.example.LibraryManagement.exception.book;

import com.example.LibraryManagement.exception.base.BadRequestException;

import static com.example.LibraryManagement.constant.ExceptionCode.BORROWING_ALREADY_EXIST_EXCEPTION;

public class BorrowingAlreadyExistException extends BadRequestException {
    public BorrowingAlreadyExistException() {
        setCode(BORROWING_ALREADY_EXIST_EXCEPTION);
    }
}

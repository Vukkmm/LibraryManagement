package com.example.LibraryManagement.exception.book;

import com.example.LibraryManagement.exception.base.NotFoundException;

import static com.example.LibraryManagement.constant.ExceptionCode.BORROWING_NOT_FOUND_EXCEPTION;

public class BorrowingNotFoundException extends NotFoundException {
    public BorrowingNotFoundException() {
        setCode(BORROWING_NOT_FOUND_EXCEPTION);
    }
}

package com.example.LibraryManagement.exception.book;

import com.example.LibraryManagement.exception.base.BadRequestException;

import static com.example.LibraryManagement.constant.ExceptionCode.BOOK_ALREADY_EXIST_EXCEPTION;

public class BookAlreadyExistException extends BadRequestException {
    public BookAlreadyExistException() {
        setCode(BOOK_ALREADY_EXIST_EXCEPTION);
    }
}

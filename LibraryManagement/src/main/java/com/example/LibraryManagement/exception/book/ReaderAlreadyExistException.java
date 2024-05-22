package com.example.LibraryManagement.exception.book;

import com.example.LibraryManagement.exception.base.BadRequestException;

import static com.example.LibraryManagement.constant.ExceptionCode.READER_ALREADY_EXIST_EXCEPTION;

public class ReaderAlreadyExistException extends BadRequestException {
    public ReaderAlreadyExistException() {
        setCode(READER_ALREADY_EXIST_EXCEPTION);
    }
}

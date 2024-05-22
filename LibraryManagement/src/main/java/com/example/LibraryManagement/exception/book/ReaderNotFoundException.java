package com.example.LibraryManagement.exception.book;

import com.example.LibraryManagement.exception.base.NotFoundException;

import static com.example.LibraryManagement.constant.ExceptionCode.READER_NOT_FOUND_EXCEPTION;

public class ReaderNotFoundException extends NotFoundException {
    public ReaderNotFoundException() {
        setCode(READER_NOT_FOUND_EXCEPTION);
    }
}

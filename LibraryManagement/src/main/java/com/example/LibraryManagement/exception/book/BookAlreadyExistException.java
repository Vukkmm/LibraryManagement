package com.example.LibraryManagement.exception.book;

import com.example.LibraryManagement.exception.base.BadRequestException;

import static com.example.LibraryManagement.constant.ExceptionCode.Book_Already_Exist_Exception;

public class BookAlreadyExistException extends BadRequestException {
    public BookAlreadyExistException() {
        setCode(Book_Already_Exist_Exception);
    }
}

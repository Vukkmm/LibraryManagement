package com.example.LibraryManagement.exception.book;

import com.example.LibraryManagement.exception.base.BadRequestException;

import static com.example.LibraryManagement.constant.ExceptionCode.CATEGORY_ALREADY_EXIST_EXCEPTION;

public class CategoryAlreadyExistException extends BadRequestException {
    public CategoryAlreadyExistException(){
        setCode(CATEGORY_ALREADY_EXIST_EXCEPTION);
    }
}

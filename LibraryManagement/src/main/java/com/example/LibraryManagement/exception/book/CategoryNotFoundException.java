package com.example.LibraryManagement.exception.book;

import com.example.LibraryManagement.exception.base.NotFoundException;

import static com.example.LibraryManagement.constant.ExceptionCode.CATEGORY_NOT_FOUND_EXCEPTION;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException(){
        setCode(CATEGORY_NOT_FOUND_EXCEPTION);
    }
}

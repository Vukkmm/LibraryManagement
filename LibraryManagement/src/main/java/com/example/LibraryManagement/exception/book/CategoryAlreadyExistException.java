package com.example.LibraryManagement.exception.book;

import com.example.LibraryManagement.exception.base.BadRequestException;

import static com.example.LibraryManagement.constant.ExceptionCode.Category_Already_Exist_Exception;

public class CategoryAlreadyExistException extends BadRequestException {
    public CategoryAlreadyExistException(){
        setCode(Category_Already_Exist_Exception);
    }
}

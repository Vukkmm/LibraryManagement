package com.example.LibraryManagement.exception.book;

import com.example.LibraryManagement.exception.base.NotFoundException;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException(){
        setCode("com.example.LibraryManagement.exception.category.CategoryNotFoundException");
    }
}

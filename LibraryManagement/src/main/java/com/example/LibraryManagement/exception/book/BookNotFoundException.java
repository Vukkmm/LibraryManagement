package com.example.LibraryManagement.exception.book;

import com.example.LibraryManagement.exception.base.NotFoundException;
import org.springframework.data.domain.Page;

import static com.example.LibraryManagement.constant.ExceptionCode.BOOK_NOT_FOUND_EXCEPTION;

public class BookNotFoundException extends NotFoundException {
    public BookNotFoundException () {
        setCode(BOOK_NOT_FOUND_EXCEPTION);
    }
}

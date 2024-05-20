package com.example.LibraryManagement.service.book;

import com.example.LibraryManagement.dto.request.BookRequest;
import com.example.LibraryManagement.dto.response.BookResponse;

public interface BookService {
    BookResponse create(BookRequest request);

}

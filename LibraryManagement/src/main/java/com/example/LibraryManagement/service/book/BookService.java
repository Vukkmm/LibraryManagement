package com.example.LibraryManagement.service.book;

import com.example.LibraryManagement.dto.base.PageResponse;
import com.example.LibraryManagement.dto.request.BookRequest;
import com.example.LibraryManagement.dto.response.BookResponse;

public interface BookService {
    BookResponse create(BookRequest request);

    PageResponse<BookResponse> list(String keyword, int size, int page, boolean isAll) ;

    BookResponse detail(String id);

    void delete(String id);

}

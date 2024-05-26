package com.example.LibraryManagement.service.book;

import com.example.LibraryManagement.dto.base.PageResponse;
import com.example.LibraryManagement.dto.request.BorrowingRequest;
import com.example.LibraryManagement.dto.response.BorrowingResponse;

public interface BorrowingService {
    BorrowingResponse create(BorrowingRequest request) ;

    PageResponse<BorrowingResponse> list(String keyword, int size, int page, boolean isAll);

    BorrowingResponse detail(String id);

    void delete(String id);

    BorrowingResponse update(String id, BorrowingRequest request);

}

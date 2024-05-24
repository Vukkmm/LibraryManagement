package com.example.LibraryManagement.service.book;

import com.example.LibraryManagement.dto.request.BorrowingRequest;
import com.example.LibraryManagement.dto.response.BorrowingResponse;

public interface BorrowingService {
    BorrowingResponse create(BorrowingRequest request) ;
}

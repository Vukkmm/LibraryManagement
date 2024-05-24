package com.example.LibraryManagement.facade;

import com.example.LibraryManagement.dto.request.BorrowingRequest;
import com.example.LibraryManagement.dto.response.BorrowingResponse;

public interface BorrowingFacadeService {
    BorrowingResponse create(BorrowingRequest request);
}

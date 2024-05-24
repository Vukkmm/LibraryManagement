package com.example.LibraryManagement.facade;

import com.example.LibraryManagement.dto.request.BorrowingRequest;
import com.example.LibraryManagement.dto.response.BorrowingResponse;
import org.hibernate.sql.Update;

public interface BorrowingFacadeService {
    BorrowingResponse create(BorrowingRequest request);

    BorrowingResponse update(String id, BorrowingRequest request);
}

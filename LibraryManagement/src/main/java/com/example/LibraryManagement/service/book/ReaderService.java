package com.example.LibraryManagement.service.book;

import com.example.LibraryManagement.dto.base.PageResponse;
import com.example.LibraryManagement.dto.request.ReaderRequest;
import com.example.LibraryManagement.dto.response.ReaderResponse;

public interface ReaderService {
    ReaderResponse create(ReaderRequest request);

    PageResponse<ReaderResponse> list(String keyword, int size, int page, boolean isAll);

    ReaderResponse detail(String id);

    void delete(String id);

    ReaderResponse update(String id, ReaderRequest request);

}

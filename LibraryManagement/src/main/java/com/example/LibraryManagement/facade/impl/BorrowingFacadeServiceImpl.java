package com.example.LibraryManagement.facade.impl;

import com.example.LibraryManagement.dto.request.BorrowingRequest;
import com.example.LibraryManagement.dto.response.BookResponse;
import com.example.LibraryManagement.dto.response.BorrowingResponse;
import com.example.LibraryManagement.dto.response.ReaderResponse;
import com.example.LibraryManagement.facade.BorrowingFacadeService;
import com.example.LibraryManagement.service.book.BookService;
import com.example.LibraryManagement.service.book.BorrowingService;
import com.example.LibraryManagement.service.book.ReaderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BorrowingFacadeServiceImpl implements BorrowingFacadeService {
    private final BookService bookService;
    private final ReaderService readerService;
    private final BorrowingService borrowingService;


    @Override
    @Transactional
    public BorrowingResponse create(BorrowingRequest request) {
        log.info("(create) request : {}", request);
        BorrowingResponse response = borrowingService.create(request);
        BookResponse bookResponse = bookService.detail(request.getBookId());
        ReaderResponse readerResponse = readerService.detail(request.getReaderId());

        return new BorrowingResponse(response.getId(),
                response.getBorrowDate(), response.getDueDate(), response.getReturnDate(), response.getStatus(),
                bookResponse,readerResponse);
    }
}

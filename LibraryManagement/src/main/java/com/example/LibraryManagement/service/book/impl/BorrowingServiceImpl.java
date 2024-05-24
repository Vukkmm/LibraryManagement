package com.example.LibraryManagement.service.book.impl;

import com.example.LibraryManagement.dto.base.PageResponse;
import com.example.LibraryManagement.dto.request.BorrowingRequest;
import com.example.LibraryManagement.dto.response.BorrowingResponse;
import com.example.LibraryManagement.entity.book.Borrowing;
import com.example.LibraryManagement.repository.book.BorrowingRepository;
import com.example.LibraryManagement.service.book.BorrowingService;
import com.example.LibraryManagement.utils.DateUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {
    private final BorrowingRepository repository;

    @Override
    @Transactional
    public BorrowingResponse create(BorrowingRequest request) {
        log.info("(create) request : {}", request);
        Borrowing borrowing = new Borrowing(
                    request.getBookId(),
                    request.getReaderId(),
                    request.getBorrowDate(),
                    request.getDueDate(),
                    request.getReturnDate(),
                    request.getStatus()
                    );
        borrowing.setBorrowDate(DateUtils.getCurrentDateString());
        borrowing.setDueDate(DateUtils.getDueToDateString());
        repository.save(borrowing);
        return new BorrowingResponse(
                borrowing.getId(),
                borrowing.getBorrowDate(),
                borrowing.getDueDate(),
                borrowing.getRetunnDate(),
                borrowing.getStatus());
    }

    @Override
    public PageResponse<BorrowingResponse> list(String keyword, int size, int page, boolean isAll) {
        log.info("(list) keyword : {}, size : {}, page : {}, isAll : {}", keyword, size, page, isAll);
        Page<BorrowingResponse> responses = isAll ? repository.findAllBorrowing(PageRequest.of(page, size))
                : repository.search(PageRequest.of(page, size), keyword);
        return PageResponse.of(responses.getContent(), responses.getNumberOfElements());
    }


}

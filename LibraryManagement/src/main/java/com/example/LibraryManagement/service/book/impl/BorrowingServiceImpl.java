package com.example.LibraryManagement.service.book.impl;

import com.example.LibraryManagement.constant.EnumStatus;
import com.example.LibraryManagement.dto.base.PageResponse;
import com.example.LibraryManagement.dto.request.BorrowingRequest;
import com.example.LibraryManagement.dto.response.BorrowingResponse;
import com.example.LibraryManagement.entity.book.Borrowing;
import com.example.LibraryManagement.exception.book.BookNotFoundException;
import com.example.LibraryManagement.exception.book.BorrowingAlreadyExistException;
import com.example.LibraryManagement.exception.book.BorrowingNotFoundException;
import com.example.LibraryManagement.exception.book.ReaderNotFoundException;
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
        borrowing.setBorrowDate(DateUtils.getCurrentDateTimeString());
        borrowing.setDueDate(DateUtils.getDueToDateTimeString());
        borrowing.setRetunnDate(" ");
        borrowing.setStatus(EnumStatus.NOT_YET_RETURNED.getStatus());
        repository.save(borrowing);
        return getBorrowingResponse(borrowing);
    }

    @Override
    public PageResponse<BorrowingResponse> list(String keyword, int size, int page, boolean isAll) {
        log.info("(list) keyword : {}, size : {}, page : {}, isAll : {}", keyword, size, page, isAll);
        Page<BorrowingResponse> responses = isAll ? repository.findAllBorrowing(PageRequest.of(page, size))
                : repository.search(PageRequest.of(page, size), keyword);
        return PageResponse.of(responses.getContent(), responses.getNumberOfElements());
    }


    @Override
    public BorrowingResponse detail(String id) {
        log.info("(detail) id : {}", id);
        this.find(id);
        return repository.detail(id);
    }


    @Override
    @Transactional
    public void delete(String id) {
        log.info("(delete) id : {}", id);
        this.checkIsDelete(id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public BorrowingResponse update(String id, BorrowingRequest request) {
        log.info("(update) id : {}, request : {}", id, request);
        Borrowing borrowing = find(id);
        this.checkStatus(borrowing.getStatus());
        this.checkBookIdForUpdate(borrowing.getBookId(), request.getBookId());
        this.checkReaderIdForUpdate(borrowing.getReaderId(), request.getReaderId());
        borrowing.setRetunnDate(DateUtils.getCurrentDateTimeString());
        borrowing.setStatus(EnumStatus.RETURNED.getStatus());
        repository.save(borrowing);
        return getBorrowingResponse(borrowing);

    }

    @Override
    @Transactional
    public BorrowingResponse softDelete(String id) {
        log.info("(softDelete) id : {}", id);
        Borrowing borrowing = find(id);
        borrowing.setDeleted(true);
        repository.save(borrowing);
        return repository.detail(id);
    }

    private void checkStatus(String status) {
        log.debug("(checkStatus) status : {}", status);
        if(status.equals("RETURNED")) {
            throw new BorrowingAlreadyExistException();
        }
    }
    private BorrowingResponse getBorrowingResponse(Borrowing borrowing) {
        log.debug("(getBorrowingResponse) borrowing : {}", borrowing);
        return new BorrowingResponse(
                borrowing.getId(),
                borrowing.getBorrowDate(),
                borrowing.getDueDate(),
                borrowing.getRetunnDate(),
                borrowing.getStatus());
    }

    private void checkReaderIdForUpdate(String readerId, String readerIdRequest) {
        log.debug("(checkReaderIdForUpdate) readerId :  {}, readerIdRequest : {}",   readerId, readerIdRequest );
        if(!readerId.equals(readerIdRequest)) {
            throw new ReaderNotFoundException();
        }
    }

    private void checkBookIdForUpdate(String bookId, String bookIdRequest) {
        log.debug("(checkBookIdForUpdate) bookId :  {}, bookIdRequest : {}",   bookId, bookIdRequest );
        if(!bookId.equals(bookIdRequest)) {
            throw new BookNotFoundException();
        }
    }

    private Borrowing find(String id) {
        log.debug("(find) {}", id);
        Borrowing borrowing = repository.findById(id).orElseThrow(BorrowingNotFoundException::new);
        if (borrowing.isDeleted()) {
            throw new BorrowingNotFoundException();
        }
        return borrowing;
    }

    private void checkIsDelete(String id) {
        log.debug("(checkIsDelete) {}", id);
        Borrowing borrowing = repository.findById(id).orElseThrow(BorrowingNotFoundException::new);
        if (!borrowing.isDeleted()) {
            throw new BorrowingNotFoundException();
        }
    }


}

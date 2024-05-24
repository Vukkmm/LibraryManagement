package com.example.LibraryManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingResponse {
    private String id;
    private String bookId;
    private String readerId;
    private BookResponse bookResponse;
    private ReaderResponse readerResponse;
    private String borrowDate;
    private String dueDate;
    private String returnDate;
    private String status;

    public BorrowingResponse(String id, String borrowDate, String dueDate, String returnDate, String status) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public BorrowingResponse(String id, String borrowDate, String dueDate, String returnDate, String status, BookResponse bookResponse, ReaderResponse readerResponse) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
        this.bookResponse = bookResponse;
        this.readerResponse = readerResponse;
    }

    public BorrowingResponse(String bookId, String readerId, String borrowDate, String dueDate, String returnDate, String status) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }
}

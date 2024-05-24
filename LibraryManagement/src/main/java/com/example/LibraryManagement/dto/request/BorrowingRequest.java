package com.example.LibraryManagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class BorrowingRequest {
    private String bookId;
    private String readerId;
    private String borrowDate;
    private String dueDate;
    private String returnDate;
    private String status;
}

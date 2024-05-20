package com.example.LibraryManagement.entity.book;

import com.example.LibraryManagement.entity.base.BaseEntityWithUpdater;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "borrowings")
public class Borrowing extends BaseEntityWithUpdater {
    @Column(name = "book_id")
    private String bookId;
    @Column(name = "reader_id")
    private String readerId;
    @Column(name = "borrow_date")
    private String borrowDate;
    @Column(name = "due_date")
    private String dueDate;
    @Column(name = "return_date")
    private String retunnDate;
    @Column(name = "status")
    private String status;
    @Column(name = "is_deleted")
    private boolean isDeleted;

}

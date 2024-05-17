package com.example.LibraryManagement.repository.book;

import com.example.LibraryManagement.entity.book.Borrowing;
import com.example.LibraryManagement.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends BaseRepository<Borrowing> {
}

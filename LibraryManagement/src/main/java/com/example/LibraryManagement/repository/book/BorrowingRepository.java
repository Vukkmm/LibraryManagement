package com.example.LibraryManagement.repository.book;

import com.example.LibraryManagement.dto.response.BookResponse;
import com.example.LibraryManagement.dto.response.BorrowingResponse;
import com.example.LibraryManagement.entity.book.Borrowing;
import com.example.LibraryManagement.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends BaseRepository<Borrowing> {
    @Query(""" 
     SELECT new com.example.LibraryManagement.dto.response.BorrowingResponse    
    (r.id, r.bookId, r.readerId, r.borrowDate, r.dueDate, r.retunnDate, r.status)
    FROM Borrowing r
    WHERE r.isDeleted = false
    """)
    Page<BorrowingResponse> findAllBorrowing(Pageable pageable);

    @Query("""
       SELECT new com.example.LibraryManagement.dto.response.BorrowingResponse
    (r.id, r.bookId, r.readerId, r.borrowDate, r.dueDate, r.retunnDate, r.status)
       FROM Borrowing r
       WHERE (:keyword is null or
      (lower(r.bookId) LIKE lower(concat('%', :keyword, '%'))) OR
      (lower(r.readerId) LIKE lower(concat('%', :keyword, '%'))) OR
      (lower(r.borrowDate) LIKE lower(concat('%', :keyword, '%'))) OR
      (lower(r.dueDate) LIKE lower(concat('%', :keyword, '%'))) OR
      (lower(r.retunnDate) LIKE lower(concat('%', :keyword, '%'))) OR
      (lower(r.status) LIKE lower(concat('%', :keyword, '%'))) 
      ) AND r.isDeleted = false
      """)
    Page<BorrowingResponse> search(Pageable pageable, String keyword);




}

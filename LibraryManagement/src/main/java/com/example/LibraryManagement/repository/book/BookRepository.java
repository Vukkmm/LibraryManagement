package com.example.LibraryManagement.repository.book;

import com.example.LibraryManagement.entity.book.Book;
import com.example.LibraryManagement.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends BaseRepository<Book> {
    @Query("""
        SELECT CASE WHEN COUNT(r) > 0 
        THEN true ELSE false END FROM Book r
        WHERE r.title = :title AND r.isDeleted = false 
        """)
    boolean checkTitleExist(String title);
}

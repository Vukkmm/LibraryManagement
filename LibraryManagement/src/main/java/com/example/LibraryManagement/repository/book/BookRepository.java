package com.example.LibraryManagement.repository.book;

import com.example.LibraryManagement.dto.response.BookResponse;
import com.example.LibraryManagement.dto.response.CategoryResponse;
import com.example.LibraryManagement.entity.book.Book;
import com.example.LibraryManagement.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(""" 
     SELECT new com.example.LibraryManagement.dto.response.BookResponse
    (r.id, r.title, r.author, r.description, r.publicationYear, r.categoryId)
    FROM Book r
    WHERE r.isDeleted = false
    """)
    Page<BookResponse> findAllBook(Pageable pageable);

    @Query("""
       SELECT new com.example.LibraryManagement.dto.response.BookResponse
      (r.id, r.title, r.author, r.description, r.publicationYear, r.categoryId)
       FROM Book r
       WHERE (:keyword is null or
      (lower(r.title) LIKE lower(concat('%', :keyword, '%'))) OR
      (lower(r.author) LIKE lower(concat('%', :keyword, '%'))) OR
      (lower(r.description) LIKE lower(concat('%', :keyword, '%'))) OR
      (lower(r.publicationYear) LIKE lower(concat('%', :keyword, '%'))) OR
      (lower(r.categoryId) LIKE lower(concat('%', :keyword, '%'))) 
      ) AND r.isDeleted = false
      """)
    Page<BookResponse> search(Pageable pageable, String keyword);



}

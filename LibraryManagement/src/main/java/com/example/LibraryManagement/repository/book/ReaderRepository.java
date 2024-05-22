package com.example.LibraryManagement.repository.book;

import com.example.LibraryManagement.dto.response.CategoryResponse;
import com.example.LibraryManagement.dto.response.ReaderResponse;
import com.example.LibraryManagement.entity.book.Reader;
import com.example.LibraryManagement.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends BaseRepository<Reader> {

    @Query("""
         SELECT CASE WHEN COUNT(r) > 0
         THEN true ELSE false END FROM Reader r
         WHERE r.name = :name AND r.isDeleted = false
        """)
    boolean checkExist(String name);

    @Query(""" 
     SELECT new com.example.LibraryManagement.dto.response.ReaderResponse
    (r.id,r.name, r.email, r.phoneNumber )
    FROM Reader r
    WHERE r.isDeleted = false
    """)
    Page<ReaderResponse> findAllCategory(Pageable pageable);

    @Query("""
       SELECT new com.example.LibraryManagement.dto.response.ReaderResponse
      (r.id,r.name, r.email, r.phoneNumber )
       FROM Reader r
       WHERE (:keyword is null or
      (lower(r.name) LIKE lower(concat('%', :keyword, '%'))) OR
      (lower(r.email) LIKE lower(concat('%', :keyword, '%'))) OR
      (lower(r.phoneNumber) LIKE lower(concat('%', :keyword, '%')))
      ) AND r.isDeleted = false
      """)
    Page<ReaderResponse> search(Pageable pageable, String keyword);

    @Query("""
        SELECT new com.example.LibraryManagement.dto.response.ReaderResponse
        (r.id,r.name, r.email, r.phoneNumber)
        FROM Reader r
        WHERE r.id=:id AND r.isDeleted= false
        """)
    ReaderResponse detail(String id);
}

package com.example.LibraryManagement.repository.book;

import com.example.LibraryManagement.dto.response.CategoryResponse;
import com.example.LibraryManagement.entity.book.Category;
import com.example.LibraryManagement.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category> {
    @Query("""
         SELECT CASE WHEN COUNT(r) > 0
         THEN true ELSE false END FROM Category r
         WHERE r.name = :name AND r.isDeleted = false
        """)
    boolean checkExist(String name);


    @Query(""" 
     SELECT new com.example.LibraryManagement.dto.response.CategoryResponse
    (r.id, r.name, r.description)
    FROM Category r
    WHERE r.isDeleted = false
    """)
    Page<CategoryResponse> findAllCategory(Pageable pageable);

    @Query("""
       SELECT new com.example.LibraryManagement.dto.response.CategoryResponse
      (r.id,r.name, r.description)
       FROM Category r
       WHERE (:keyword is null or
      (lower(r.name) LIKE lower(concat('%', :keyword, '%'))) OR
      (lower(r.description) LIKE lower(concat('%', :keyword, '%')))
      ) AND r.isDeleted = false
      """)
    Page<CategoryResponse> search(Pageable pageable, String keyword);


    @Query("""
        SELECT new com.example.LibraryManagement.dto.response.CategoryResponse
        (r.id,r.name, r.description)
        FROM Category r
        WHERE r.id=:id AND r.isDeleted= false
        """)
    CategoryResponse detail(String id);
}

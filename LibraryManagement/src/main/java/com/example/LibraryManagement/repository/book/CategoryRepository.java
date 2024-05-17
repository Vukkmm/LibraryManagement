package com.example.LibraryManagement.repository.book;

import com.example.LibraryManagement.entity.book.Category;
import com.example.LibraryManagement.repository.BaseRepository;
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
}

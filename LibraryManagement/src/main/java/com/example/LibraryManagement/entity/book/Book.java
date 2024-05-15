package com.example.LibraryManagement.entity.book;

import com.example.LibraryManagement.entity.base.BaseEntityWithUpdater;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "books")
public class Book extends BaseEntityWithUpdater {
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "publication_year")
    private String publicationYear;
    @Column(name = "description")
    private String description;
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "is_deleted")
    private boolean isDeleted;
}

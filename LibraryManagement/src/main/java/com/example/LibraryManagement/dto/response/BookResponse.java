package com.example.LibraryManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private String id;
    private String title;
    private String author;
    private String publicationYear;
    private String description;
    private CategoryResponse categoryResponse;
    private String categoryId;

    public BookResponse(String id, String title, String author, String publicationYear, String description, String categoryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.description = description;
        this.categoryId = categoryId;
    }

    public BookResponse(String id, String title, String author, String publicationYear, String description, CategoryResponse categoryResponse) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.description = description;
        this.categoryResponse = categoryResponse;
    }
}

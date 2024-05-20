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

}

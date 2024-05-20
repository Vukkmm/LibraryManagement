package com.example.LibraryManagement.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class BookRequest {
    private String title;
    private String author;
    private String publicationYear;
    private String description;
    private String categoryId;
}

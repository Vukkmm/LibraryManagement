package com.example.LibraryManagement.dto.response;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryResponse {
    private String id;
    private String name;
    private String description;
}

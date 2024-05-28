package com.example.LibraryManagement.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CategoryRequest {
    private String name;
    private String description;


}

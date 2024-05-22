package com.example.LibraryManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaderResponse {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
}

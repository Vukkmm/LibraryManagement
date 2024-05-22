package com.example.LibraryManagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ReaderRequest {
    private String name;
    private String email;
    private String phoneNumber;

}

package com.example.LibraryManagement.service;

import com.example.LibraryManagement.dto.request.CategoryRequest;
import com.example.LibraryManagement.dto.response.CategoryResponse;

public interface CategoryService {

    CategoryResponse create(CategoryRequest request);
}

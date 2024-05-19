package com.example.LibraryManagement.service.book;

import com.example.LibraryManagement.dto.base.PageResponse;
import com.example.LibraryManagement.dto.request.CategoryRequest;
import com.example.LibraryManagement.dto.response.CategoryResponse;

public interface CategoryService {

    CategoryResponse create(CategoryRequest request);

    PageResponse<CategoryResponse> list(String keyword, int size, int page, boolean isAll);

    CategoryResponse detail(String id);

    void delete(String id);

    CategoryResponse  update(String id, CategoryRequest request);
}

package com.example.LibraryManagement.service;


import com.example.LibraryManagement.dto.request.CategoryRequest;
import com.example.LibraryManagement.dto.response.CategoryResponse;
import com.example.LibraryManagement.entity.book.Category;
import com.example.LibraryManagement.exception.book.CategoryAlreadyExistException;
import com.example.LibraryManagement.exception.book.CategoryNotFoundException;
import com.example.LibraryManagement.repository.book.CategoryRepository;
import com.example.LibraryManagement.service.book.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

@WebMvcTest(CategoryService.class)
//@ContextConfiguration(classes = Configuration.class)
public class CategoryServiceTest {

    @MockBean
    private CategoryRepository repository;

    @Autowired
    private CategoryService categoryService;

    private CategoryRequest mockCategoryRequest() {
        return CategoryRequest.of("co tich", "good");
    }

    private Category mockCategory() {
        return new Category("co tich", "good");
    }


    @Test
    public void testCreate_WhenNameCategoryAlready_ThrowException() {
        CategoryRequest request = mockCategoryRequest();

        Mockito.when(repository.checkExist(request.getName())).thenReturn(true);

        Assertions.assertThrows(CategoryAlreadyExistException.class, () -> categoryService.create(mockCategoryRequest()));
    }

    @Test
    public void testCreate_WhenSuccess_ReturnCategoryResponse() {
        CategoryRequest mockRequest = mockCategoryRequest();
        Category mockEntity = mockCategory();

        Mockito.when(repository.checkExist(mockRequest.getName())).thenReturn(false);
        Mockito.when(repository.save(mockEntity)).thenReturn(mockEntity);

        CategoryResponse response = categoryService.create(mockRequest);
        Assertions.assertEquals(mockEntity.getName(), response.getName());
        Assertions.assertEquals(mockEntity.getDescription(), response.getDescription());
    }

    @Test
    public void testDetail_WhenIsCategoryNotFound_ReturnThrowException() {
        Mockito.when(repository.findById("1")).thenReturn(Optional.empty());
        Assertions.assertThrows(CategoryNotFoundException.class, ()-> categoryService.detail("1"));
    }




}

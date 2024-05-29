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
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    private CategoryResponse mockCategoryResponse(){
            return new CategoryResponse("1", "co tich", "good");
    };


    @Test
    public void testCreate_WhenNameCategoryAlready_ThrowException() {
        CategoryRequest request = mockCategoryRequest();

        when(repository.checkExist(request.getName())).thenReturn(true);

        assertThrows(CategoryAlreadyExistException.class, () -> categoryService.create(mockCategoryRequest()));
    }

    @Test
    public void testCreate_WhenSuccess_ReturnCategoryResponse() {
        CategoryRequest mockRequest = mockCategoryRequest();
        Category mockEntity = mockCategory();

        when(repository.checkExist(mockRequest.getName())).thenReturn(false);
        when(repository.save(mockEntity)).thenReturn(mockEntity);

        CategoryResponse response = categoryService.create(mockRequest);
        Assertions.assertEquals(mockEntity.getName(), response.getName());
        Assertions.assertEquals(mockEntity.getDescription(), response.getDescription());
    }

    @Test
    public void testDetail_WhenIsCategoryNotFound_ReturnThrowException() {
        when(repository.findById("1")).thenReturn(Optional.empty());
        assertThrows(CategoryNotFoundException.class, ()-> categoryService.detail("1"));
    }

    @Test
    public void testDetail_WhenIsDeleteTrue_ReturnThrowException() {
        Category mockEntity = mock(Category.class);

        Mockito.when(repository.findById("1")).thenReturn(Optional.of(mockEntity));
        Mockito.when(mockEntity.isDeleted()).thenReturn(true);
        Assertions.assertThrows(CategoryNotFoundException.class, () -> categoryService.detail("1"));
    }

    @Test
    public void testDetail_WhenSuccess_ReturnThrowException() {
        CategoryResponse response = mockCategoryResponse();
        Category mockEntity = mock(Category.class);

        Mockito.when(repository.findById("1")).thenReturn(Optional.of(mockEntity));
        Mockito.when(mockEntity.isDeleted()).thenReturn(false);
        Mockito.when(repository.detail("1")).thenReturn(response);

        CategoryResponse responses = categoryService.detail("1");
        Assertions.assertEquals(response.getName(), responses.getName());
        Assertions.assertEquals(response.getDescription(), responses.getDescription());
    }

    @Test
    public void testDelete_WhenIdNotFound_ReturnThrowException() {
        Mockito.when(repository.findById("1")).thenReturn(Optional.empty());
        Assertions.assertThrows(CategoryNotFoundException.class, ()-> categoryService.delete("1"));
    }
}

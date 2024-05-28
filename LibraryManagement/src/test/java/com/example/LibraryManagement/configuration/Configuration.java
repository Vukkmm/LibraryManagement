package com.example.LibraryManagement.configuration;

import com.example.LibraryManagement.repository.book.CategoryRepository;
import com.example.LibraryManagement.service.book.CategoryService;
import com.example.LibraryManagement.service.book.impl.CategoryServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class Configuration {
    @Bean
    public CategoryService categoryService(CategoryRepository repository) {
        return  new CategoryServiceImpl(repository);
    }
}

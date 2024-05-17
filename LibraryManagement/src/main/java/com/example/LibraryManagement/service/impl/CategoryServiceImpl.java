package com.example.LibraryManagement.service.impl;

import com.example.LibraryManagement.dto.base.PageResponse;
import com.example.LibraryManagement.dto.request.CategoryRequest;
import com.example.LibraryManagement.dto.response.CategoryResponse;
import com.example.LibraryManagement.entity.book.Category;
import com.example.LibraryManagement.exception.book.CategoryAlreadyExistException;
import com.example.LibraryManagement.repository.book.CategoryRepository;
import com.example.LibraryManagement.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    @Override
    public CategoryResponse create(CategoryRequest request) {
        log.info("(create) request : {}", request);
        this.checkExist(request.getName());
        Category category = new Category(
                request.getName(),
                request.getDescription()
        );
        repository.save(category);
        return new CategoryResponse(category.getId(), category.getName(), category.getDescription());
    }

    @Override
    public PageResponse<CategoryResponse> list(String keyword, int size, int page, boolean isAll) {
        log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}", keyword, size, page, isAll);
        Page<CategoryResponse> responses = isAll ? repository.findAllCategory(PageRequest.of(page, size))
                : repository.search(PageRequest.of(page, size), keyword);

        return PageResponse.of(responses.getContent(), responses.getNumberOfElements());
    }

    private void checkExist(String name) {
        log.debug("checkExist() {}", name);
        repository.checkExist(name);
        if(repository.checkExist(name)) {
            log.error("checkExist:{}", name);
            throw  new CategoryAlreadyExistException();
        }
    }

}

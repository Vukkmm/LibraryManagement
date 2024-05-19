package com.example.LibraryManagement.service.impl.book;

import com.example.LibraryManagement.dto.base.PageResponse;
import com.example.LibraryManagement.dto.request.CategoryRequest;
import com.example.LibraryManagement.dto.response.CategoryResponse;
import com.example.LibraryManagement.entity.book.Category;
import com.example.LibraryManagement.exception.book.CategoryAlreadyExistException;
import com.example.LibraryManagement.exception.book.CategoryNotFoundException;
import com.example.LibraryManagement.repository.book.CategoryRepository;
import com.example.LibraryManagement.service.book.CategoryService;
import jakarta.transaction.Transactional;
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

    @Override
    public CategoryResponse detail(String id) {
        log.info("(detail) id : {}", id);
        this.find(id);
        return repository.detail(id);
    }

    @Transactional
    @Override
    public void delete(String id) {
        log.info("(delete)id: {}", id);
        this.find(id);
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public CategoryResponse update(String id, CategoryRequest request) {
        log.info("(update) id: {}, request : {}", id, request);
        Category category = find(id);
        this.checkNameForUpdate(category.getName(),  request.getName());
        log.debug("check name of category already exists when update");
        setValueUpdate(category, request);
        repository.save(category);
        return new CategoryResponse(category.getId(), category.getName(), category.getDescription());
    }

    private void setValueUpdate(Category category, CategoryRequest request) {
        log.info("(setValueUpdate)");
        category.setName(request.getName());
        category.setDescription(request.getDescription());
    }

    private void checkExist(String name) {
        log.debug("checkExist() {}", name);
        repository.checkExist(name);
        if(repository.checkExist(name)) {
            log.error("checkExist:{}", name);
            throw  new CategoryAlreadyExistException();
        }
    }

    private Category find(String id) {
        log.debug("findById() {}", id);
        Category category =repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        if (category.isDeleted()) {
            throw new CategoryNotFoundException();
        }
        return category;
    }

    private void checkNameForUpdate(String name, String nameRequest) {
        log.debug("checkNameForUpdate() {} {}", name, nameRequest);
        if(!name.equals(nameRequest)) {
            this.checkExist(nameRequest);
        }
    }

}

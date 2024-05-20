package com.example.LibraryManagement.service.impl.book;

import com.example.LibraryManagement.dto.request.BookRequest;
import com.example.LibraryManagement.dto.response.BookResponse;
import com.example.LibraryManagement.dto.response.CategoryResponse;
import com.example.LibraryManagement.entity.book.Book;
import com.example.LibraryManagement.entity.book.Category;
import com.example.LibraryManagement.exception.book.BookAlreadyExistException;
import com.example.LibraryManagement.exception.book.CategoryNotFoundException;
import com.example.LibraryManagement.repository.book.BookRepository;
import com.example.LibraryManagement.repository.book.CategoryRepository;
import com.example.LibraryManagement.service.book.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public BookResponse create(BookRequest request) {
        log.info("(create) request : {}", request);
        this.checkTitleExist(request.getTitle());
        Category category = checkCategoryIdExist(request.getCategoryId());
        Book book = new Book(
                request.getTitle(),
                request.getAuthor(),
                request.getDescription(),
                request.getPublicationYear(),
                request.getCategoryId()
        );
        repository.save(book);
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getPublicationYear(),
                book.getDescription(),
                new CategoryResponse(category.getId(), category.getName(), category.getDescription()));
    }

    private void checkTitleExist(String title) {
        log.debug("(checkTitleExist) title : {}", title);
        repository.checkTitleExist(title);
        if(repository.checkTitleExist(title)) {
            log.error("checkTitleExist:{}", title);
            throw  new BookAlreadyExistException();
        }
    }

    private Category checkCategoryIdExist(String categoryId) {
        log.debug("checkCategoryIdExist() {}", categoryId);
        return categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    }
}

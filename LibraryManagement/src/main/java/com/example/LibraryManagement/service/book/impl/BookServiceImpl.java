package com.example.LibraryManagement.service.book.impl;

import com.example.LibraryManagement.dto.base.PageResponse;
import com.example.LibraryManagement.dto.request.BookRequest;
import com.example.LibraryManagement.dto.response.BookResponse;
import com.example.LibraryManagement.dto.response.CategoryResponse;
import com.example.LibraryManagement.entity.book.Book;
import com.example.LibraryManagement.entity.book.Borrowing;
import com.example.LibraryManagement.entity.book.Category;
import com.example.LibraryManagement.exception.book.BookAlreadyExistException;
import com.example.LibraryManagement.exception.book.BookNotFoundException;
import com.example.LibraryManagement.exception.book.BorrowingNotFoundException;
import com.example.LibraryManagement.exception.book.CategoryNotFoundException;
import com.example.LibraryManagement.repository.book.BookRepository;
import com.example.LibraryManagement.repository.book.CategoryRepository;
import com.example.LibraryManagement.service.book.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        checkCategoryIdExist(request.getCategoryId());
        Book book = new Book(
                request.getTitle(),
                request.getAuthor(),
                request.getDescription(),
                request.getPublicationYear(),
                request.getCategoryId()
        );
        repository.save(book);
        return getBookResponse(book);
    }

    @Override
    public PageResponse<BookResponse> list(String keyword, int size, int page, boolean isAll) {
        log.info("(list) keyword : {}, size : {}, page : {}, isAll : {}", keyword, size, page, isAll);
        Page<BookResponse> responses = isAll ? repository.findAllBook(PageRequest.of(page, size))
                : repository.search(PageRequest.of(page,size), keyword);
        return PageResponse.of(responses.getContent(),responses.getNumberOfElements());
    }

    @Override
    public BookResponse detail(String id) {
        log.info("(detail) id : {}", id);
        Book book = this.find(id);
        checkCategoryIdExist(book.getCategoryId());
        return getBookResponse(book);
    }

    @Override
    @Transactional
    public void delete(String id) {
        log.info("(delete) id : {}", id);
        this.checkIsDelete(id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public BookResponse update(String id, BookRequest request) {
        log.info("(update) id : {}, request : {}", id, request);
        Book book = find(id);
        this.checkTitleForUpdate(book.getTitle(), request.getTitle());
        log.debug("check title of book already exists when update");
        setValueUpdate(book, request);
        repository.save(book);
        return  getBookResponse(book);
    }

    @Override
    @Transactional
    public BookResponse softDelete(String id) {
        log.info("(softDelete) id : {}", id);
        Book book = find(id);
        book.setDeleted(true);
        repository.save(book);
        return getBookResponse(book);
    }

    private void checkTitleForUpdate(String title, String titleRequest) {
        log.debug("checkNameForUpdate() title : {}, titleRequest {}", title, titleRequest);
        if(!title.equals(titleRequest)) {
            this.checkTitleExist(titleRequest);
        }
    }

    private void setValueUpdate(Book book, BookRequest request) {
        log.info("(setValueUpdate)");
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setDescription(request.getDescription());
        book.setPublicationYear(request.getPublicationYear());
        this.checkCategoryIdExist(request.getCategoryId());
        book.setCategoryId(request.getCategoryId());
    }

    private Book find(String id) {
        log.debug("(find) {}", id);
        Book book = repository.findById(id).orElseThrow(BookNotFoundException::new);
        if(book.isDeleted()) {
            throw new BookNotFoundException();
        }
        return book;
    }

    private CategoryResponse getCategoryResponseById(String id) {
        Category category = checkCategoryIdExist(id);
        return  new CategoryResponse(category.getId(), category.getName(), category.getDescription());
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
        Category category =  categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        if(!category.isDeleted()) {
            throw new CategoryNotFoundException();
        }
        return category;
    }

    private BookResponse getBookResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getPublicationYear(),
                getCategoryResponseById(book.getCategoryId())
        );
    }

    private void checkIsDelete(String id) {
        log.debug("(checkIsDelete) {}", id);
        Book book = repository.findById(id).orElseThrow(BookNotFoundException::new);
        if (!book.isDeleted()) {
            throw new BookNotFoundException();
        }
    }
}



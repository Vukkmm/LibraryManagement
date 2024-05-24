package com.example.LibraryManagement.controller.book;

import com.example.LibraryManagement.dto.base.PageResponse;
import com.example.LibraryManagement.dto.base.ResponseGeneral;
import com.example.LibraryManagement.dto.request.BookRequest;
import com.example.LibraryManagement.dto.response.BookResponse;
import com.example.LibraryManagement.service.message.MessageService;
import com.example.LibraryManagement.service.book.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.example.LibraryManagement.constant.CommonConstants.DEFAULT_LANGUAGE;
import static com.example.LibraryManagement.constant.CommonConstants.LANGUAGE;
import static com.example.LibraryManagement.constant.MessageCodeConstant.*;

@Slf4j
@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {
     private final BookService bookService;
     private final MessageService messageService;

     @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseGeneral<BookResponse> create(
            @Valid @RequestBody BookRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        log.info("(create) request : {}", request);
        return ResponseGeneral.ofCreated(
                messageService.getMessage(CREATE_BOOK, language),
                bookService.create(request)
        );
    }

    @GetMapping
    public ResponseGeneral<PageResponse<BookResponse>> list(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "all", defaultValue = "false", required = false) boolean isAll,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}", keyword, size, page, isAll);
        return  ResponseGeneral.ofSuccess(messageService.getMessage(LIST_BOOKS, language),
                bookService.list(keyword,size,page,isAll));
    }

    @GetMapping("{id}")
    public  ResponseGeneral<BookResponse> detail(
            @PathVariable String id,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        log.info("(detail) id : {}", id);
        return ResponseGeneral.ofSuccess(
                messageService.getMessage(DETAIL_BOOK, language),
                bookService.detail(id)
        );
    }

    @DeleteMapping("{id}")
    public ResponseGeneral<BookResponse> delete(
            @PathVariable String id,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        log.info("(delete) id : {}", id);
        bookService.delete(id);
        return ResponseGeneral.ofSuccess(messageService.getMessage(DELETE_BOOK, language));
    }

    @PutMapping("{id}")
    public ResponseGeneral<BookResponse> update(
            @PathVariable String id,
            @Valid @RequestBody BookRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ResponseGeneral.ofSuccess(messageService.getMessage(UPDATE_BOOK, language), bookService.update(id, request));
    }
}

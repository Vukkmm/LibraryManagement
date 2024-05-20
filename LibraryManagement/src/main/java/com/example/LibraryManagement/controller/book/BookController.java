package com.example.LibraryManagement.controller.book;

import com.example.LibraryManagement.dto.base.ResponseGeneral;
import com.example.LibraryManagement.dto.request.BookRequest;
import com.example.LibraryManagement.dto.response.BookResponse;
import com.example.LibraryManagement.service.MessageService;
import com.example.LibraryManagement.service.book.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.example.LibraryManagement.constant.CommonConstants.DEFAULT_LANGUAGE;
import static com.example.LibraryManagement.constant.CommonConstants.LANGUAGE;
import static com.example.LibraryManagement.constant.MessageCodeConstant.CREATE_BOOK;

@Slf4j
@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {
     private final BookService bookService;
     private final MessageService messageService;


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
}

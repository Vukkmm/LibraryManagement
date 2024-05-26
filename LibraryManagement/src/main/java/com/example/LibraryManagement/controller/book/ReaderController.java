package com.example.LibraryManagement.controller.book;

import com.example.LibraryManagement.dto.base.PageResponse;
import com.example.LibraryManagement.dto.base.ResponseGeneral;
import com.example.LibraryManagement.dto.request.ReaderRequest;
import com.example.LibraryManagement.dto.response.ReaderResponse;
import com.example.LibraryManagement.service.message.MessageService;
import com.example.LibraryManagement.service.book.ReaderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.example.LibraryManagement.constant.CommonConstants.*;
import static com.example.LibraryManagement.constant.MessageCodeConstant.*;

@Slf4j
@RestController
@RequestMapping("api/v1/readers")
@RequiredArgsConstructor
public class ReaderController {
    private final MessageService messageService;
    private final ReaderService readerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseGeneral<ReaderResponse> create(
            @Valid @RequestBody ReaderRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language

    ) {
        log.info("(create) request : {}", request);
        return ResponseGeneral.ofCreated(
                messageService.getMessage(CREATE_READER, language),
                readerService.create(request)
        );
    }

    @GetMapping
    public  ResponseGeneral<PageResponse<ReaderResponse>> list(
            @RequestParam(name = KEYWORD, required = false) String keyword,
            @RequestParam(name = SIZE, defaultValue = "10") int size,
            @RequestParam(name = PAGE, defaultValue = "0") int page,
            @RequestParam(name = ALL, defaultValue = "false", required = false) boolean isAll,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}", keyword, size, page, isAll);
        return ResponseGeneral.ofSuccess(messageService.getMessage(LIST_READER, language),
                readerService.list(keyword, size, page, isAll)
        );
    }

    @GetMapping("{id}")
    public  ResponseGeneral<ReaderResponse> detail(
            @PathVariable String id,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language

    ) {
        log.info("(detail) id : {}", id);
        return ResponseGeneral.ofSuccess(messageService.getMessage(DETAIL_READER, language),
                readerService.detail(id));
    }

    @DeleteMapping("{id}")
    public  ResponseGeneral<ReaderResponse> delete(
            @PathVariable String id,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language

    ) {
        log.info("(delete) id : {}", id);
        readerService.delete(id);
        return ResponseGeneral.ofSuccess(messageService.getMessage(DELETE_READER, language));
    }

    @PutMapping("{id}")
    public ResponseGeneral<ReaderResponse> update(
            @PathVariable String id,
            @Valid @RequestBody ReaderRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ResponseGeneral.ofSuccess(messageService.getMessage(UPDATE_READER, language), readerService.update(id, request));
    }






}

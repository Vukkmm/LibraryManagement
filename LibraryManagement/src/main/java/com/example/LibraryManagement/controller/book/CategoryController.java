package com.example.LibraryManagement.controller.book;

import com.example.LibraryManagement.dto.base.PageResponse;
import com.example.LibraryManagement.dto.base.ResponseGeneral;
import com.example.LibraryManagement.dto.request.CategoryRequest;
import com.example.LibraryManagement.dto.response.CategoryResponse;
import com.example.LibraryManagement.service.book.CategoryService;
import com.example.LibraryManagement.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.example.LibraryManagement.constant.CommonConstants.DEFAULT_LANGUAGE;
import static com.example.LibraryManagement.constant.CommonConstants.LANGUAGE;
import static com.example.LibraryManagement.constant.MessageCodeConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final MessageService messageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseGeneral<CategoryResponse> create(
            @Valid @RequestBody CategoryRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ){
        return ResponseGeneral.ofCreated(
                messageService.getMessage(CREATE_CATEGORY, language),
                categoryService.create(request)
        );
    }

    @GetMapping
    public  ResponseGeneral<PageResponse<CategoryResponse>> list(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "all", defaultValue = "false", required = false) boolean isAll,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}", keyword, size, page, isAll);
        return ResponseGeneral.ofSuccess(messageService.getMessage(LIST_CATEGORIES, language),
                categoryService.list(keyword, size, page, isAll)
        );
    }

    @GetMapping("{id}")
    public  ResponseGeneral<CategoryResponse> detail(
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        log.info("(detail) id : {}", id);
        return ResponseGeneral.ofSuccess(
                messageService.getMessage(DETAIL_CATEGORY, language),
                categoryService.detail(id)
        );
    }

    @DeleteMapping("{id}")
    public ResponseGeneral<CategoryResponse> delete(
            @PathVariable String id,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        log.info("(delete) id : {}", id);
        categoryService.delete(id);
        return ResponseGeneral.ofSuccess(messageService.getMessage(DELETE_CATEGORY, language));
    }

    @PutMapping("{id}")
    public ResponseGeneral<CategoryResponse> update(
            @PathVariable String id,
            @Valid @RequestBody CategoryRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ResponseGeneral.ofSuccess(messageService.getMessage(UPDATE_CATEGORY, language), categoryService.update(id, request));
    }

}

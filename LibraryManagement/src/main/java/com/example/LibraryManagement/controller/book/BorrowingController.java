package com.example.LibraryManagement.controller.book;

import com.example.LibraryManagement.dto.base.PageResponse;
import com.example.LibraryManagement.dto.base.ResponseGeneral;
import com.example.LibraryManagement.dto.request.BorrowingRequest;
import com.example.LibraryManagement.dto.response.BorrowingResponse;
import com.example.LibraryManagement.dto.response.CategoryResponse;
import com.example.LibraryManagement.facade.BorrowingFacadeService;
import com.example.LibraryManagement.service.message.MessageService;
import com.example.LibraryManagement.service.book.BorrowingService;
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
@RequestMapping("api/vi/borrowings")
@RequiredArgsConstructor
public class BorrowingController {
    private final MessageService messageService;
    private final BorrowingFacadeService borrowingFacadeService;
    private final BorrowingService borrowingService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseGeneral<BorrowingResponse> create(
            @Valid @RequestBody BorrowingRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language

    ) {
        log.info("(create) request : {}", request);
        return ResponseGeneral.ofCreated(
                messageService.getMessage(CREATE_BORROWING, language),
                borrowingFacadeService.create(request)
        );
    }

    @GetMapping
    public ResponseGeneral<PageResponse<BorrowingResponse>> list(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "all", defaultValue = "false", required = false) boolean isAll,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}", keyword, size, page, isAll);
        return ResponseGeneral.ofSuccess(messageService.getMessage(LIST_BORROWING, language),
                borrowingService.list(keyword, size, page, isAll));
    }

    @GetMapping("{id}")
    public  ResponseGeneral<BorrowingResponse> detail(
            @PathVariable String id,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        log.info("(detail) id : {}", id);
        return ResponseGeneral.ofSuccess(
                messageService.getMessage(DETAIL_BORROWING, language),
                borrowingService.detail(id)
        );
    }
}

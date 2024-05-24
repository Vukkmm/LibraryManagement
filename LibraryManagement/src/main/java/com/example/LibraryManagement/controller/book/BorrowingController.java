package com.example.LibraryManagement.controller.book;

import com.example.LibraryManagement.dto.base.ResponseGeneral;
import com.example.LibraryManagement.dto.request.BorrowingRequest;
import com.example.LibraryManagement.dto.response.BorrowingResponse;
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
import static com.example.LibraryManagement.constant.MessageCodeConstant.CREATE_BORROWING;

@Slf4j
@RestController
@RequestMapping("api/vi/borrowings")
@RequiredArgsConstructor
public class BorrowingController {
    private final MessageService messageService;
    private final BorrowingFacadeService borrowingFacadeService;

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
}

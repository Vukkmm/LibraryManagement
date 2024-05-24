package com.example.LibraryManagement.constant;

public class ExceptionCode {
    public static final String BAD_REQUEST_CODE = "com.example.LibraryManagement.exception.base.BadRequestException";
    public static final String CONFLICT_CODE = "com.example.LibraryManagement.exception.base.ConflictException";
    public static final String NOT_FOUND_CODE = "com.example.LibraryManagement.exception.base.NotFoundException";
    public static final String DUPLICATE_CODE = "com.example.LibraryManagement.exception.DuplicateNameException";
    public static final String USER_NOT_FOUND_CODE = "com.example.LibraryManagement.exception.base.NotFoundException.UserNotFoundException";
    public static final String GENERIC_CODE = "com.example.LibraryManagement.exception.base.GenericException";

    public static final String CATEGORY_ALREADY_EXIST_EXCEPTION = "com.example.LibraryManagement.exception.book.CategoryAlreadyExistException";

    public static final String CATEGORY_NOT_FOUND_EXCEPTION = "com.example.LibraryManagement.exception.book.CategoryNotFoundException";
    public static final String BOOK_ALREADY_EXIST_EXCEPTION = "com.example.LibraryManagement.exception.book.BookAlreadyExistException";
    public static final String BOOK_NOT_FOUND_EXCEPTION = "com.example.LibraryManagement.exception.book.BookNotFoundException";

    public static final String READER_ALREADY_EXIST_EXCEPTION = "com.example.LibraryManagement.exception.book.ReaderAlreadyExistException";
    public static final String READER_NOT_FOUND_EXCEPTION = "com.example.LibraryManagement.exception.book.ReaderNotFoundException";

    public static final String BORROWING_NOT_FOUND_EXCEPTION = "com.example.LibraryManagement.exception.book.BorrowingNotFoundException";
    public static final String BORROWING_ALREADY_EXIST_EXCEPTION = "com.example.LibraryManagement.exception.book.BorrowingAlreadyExistException";


}

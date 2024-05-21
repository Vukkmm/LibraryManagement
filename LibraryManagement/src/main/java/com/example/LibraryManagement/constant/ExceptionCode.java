package com.example.LibraryManagement.constant;

public class ExceptionCode {
    public static final String BAD_REQUEST_CODE = "com.example.LibraryManagement.exception.base.BadRequestException";
    public static final String CONFLICT_CODE = "com.example.LibraryManagement.exception.base.ConflictException";
    public static final String NOT_FOUND_CODE = "com.example.LibraryManagement.exception.base.NotFoundException";
    public static final String DUPLICATE_CODE = "com.example.LibraryManagement.exception.DuplicateNameException";
    public static final String USER_NOT_FOUND_CODE = "com.example.LibraryManagement.exception.base.NotFoundException.UserNotFoundException";
    public static final String GENERIC_CODE = "com.example.LibraryManagement.exception.base.GenericException";

    public static final String Category_Already_Exist_Exception = "com.example.LibraryManagement.exception.book.CategoryAlreadyExistException";
    public static final String Book_Already_Exist_Exception = "com.example.LibraryManagement.exception.book.BookAlreadyExistException";

    public static final String BOOK_NOT_FOUND_EXCEPTION = "com.example.LibraryManagement.exception.book.book.CategoryNotFoundException";

}

package com.example.LibraryManagement.exception.base;

import static com.example.LibraryManagement.constant.ExceptionCode.CONFLICT_CODE;
import static com.example.LibraryManagement.exception.base.StatusConstants.CONFLICT;

public class ConflictException extends BaseException{
    public ConflictException(String id, String objectName) {
        setStatus(CONFLICT);
        setCode(CONFLICT_CODE);
        addParam("id", id);
        addParam("objectName", objectName);
    }

    public ConflictException() {
        setStatus(CONFLICT);
        setCode(CONFLICT_CODE);
    }
}

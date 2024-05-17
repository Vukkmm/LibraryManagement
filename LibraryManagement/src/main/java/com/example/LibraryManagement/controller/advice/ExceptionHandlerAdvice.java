package com.example.LibraryManagement.controller.advice;

import com.example.LibraryManagement.dto.base.ResponseGeneral;
import com.example.LibraryManagement.exception.base.BadRequestException;
import com.example.LibraryManagement.exception.base.BaseException;
import com.example.LibraryManagement.exception.base.ConflictException;
import com.example.LibraryManagement.exception.base.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@ControllerAdvice
public class ExceptionHandlerAdvice {
    private final MessageSource messageSource;
    /**
     * Constructor for the ExceptionHandlerAdvice class.
     *
     * @param messageSource The MessageSource bean for retrieving localized error messages.
     */
    @Autowired
    public ExceptionHandlerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    /**
     * Handles exceptions to type BaseException. Retrieves the error code, localizes the error message,
     * and constructs a standardized response.
     *
     * @param ex     The BaseException instance.
     * @param locale The locale for message localization.
     * @return A ResponseEntity with a standardized response for the given exception.
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ResponseGeneral<Object>> handleBaseException(BaseException ex, Locale locale) {
        String message = getMessage(ex.getCode(), locale, ex.getParams());
        ResponseGeneral<Object> response = ResponseGeneral.of(ex.getStatus(), message, null);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getStatus()));
    }

    /**
     * Handles exceptions to type NotFoundException. Retrieves the error code, localizes the error message,
     * and constructs a standardized response.
     *
     * @param ex The NotFoundException instance
     * @param locale The locale for message localization.
     * @return A ResponseEntity with a not found status
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseGeneral<Object>> handleNotFoundException(NotFoundException ex, Locale locale) {
        String message = getMessage(ex.getCode(), locale, ex.getParams());
        ResponseGeneral<Object> response = ResponseGeneral.of(ex.getStatus(), message, null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles exceptions to type BadRequestException. Retrieves the error code, localizes the error message,
     * and constructs a standardized response.
     *
     * @param ex The BadRequestException instance
     * @param locale The locale for message localization.
     * @return A ResponseEntity with a bad request status
     */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseGeneral<Object>> handleBadRequestException(BadRequestException ex, Locale locale) {
        String message = getMessage(ex.getCode(), locale, ex.getParams());
        ResponseGeneral<Object> response = ResponseGeneral.of(ex.getStatus(), message, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions to type BadRequestException. Retrieves the error code, localizes the error message,
     * and constructs a standardized response.
     *
     * @param ex The ConflictException instance
     * @param locale The locale for message localization.
     * @return A ResponseEntity with a conflict status
     */
    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseGeneral<Object>> handleConflictException(ConflictException ex, Locale locale) {
        String message = getMessage(ex.getCode(), locale, ex.getParams());
        ResponseGeneral<Object> response = ResponseGeneral.of(ex.getStatus(), message, null);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    /**
     * Handles generic RuntimeExceptions. It returns a standardized response with an internal server error status.
     *
     * @param locale The locale for message localization.
     * @return A ResponseEntity with a standardized response for generic runtime exceptions.
     */
//  @ExceptionHandler(RuntimeException.class)
//  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//  public ResponseEntity<ResponseGeneral<Object>> handleGenericException(Locale locale) {
//    String message = getMessage(GENERIC_CODE, locale, null);
//    ResponseGeneral<Object> response = ResponseGeneral.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null);
//    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//  }
    /**
     * Utility method to retrieve a localized error message from the MessageSource.
     *
     * @param code   The error code to be retrieved.
     * @param locale The locale for message localization.
     * @param params Additional parameters for message formatting.
     * @return The localized error message or the error code if message retrieval fails.
     */
    private String getMessage(String code, Locale locale, Map<String, String> params) {
        try {
            return messageSource.getMessage(code, params != null ? params.values().toArray() : null, locale);
        } catch (Exception e) {
            return code;
        }
    }
}

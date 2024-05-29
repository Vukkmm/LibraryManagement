package com.example.LibraryManagement.service;

import com.example.LibraryManagement.dto.request.ReaderRequest;
import com.example.LibraryManagement.dto.response.ReaderResponse;
import com.example.LibraryManagement.entity.book.Reader;
import com.example.LibraryManagement.exception.book.EmailAlreadyExistException;
import com.example.LibraryManagement.exception.book.PhoneNumberAlreadyExistException;
import com.example.LibraryManagement.repository.book.ReaderRepository;
import com.example.LibraryManagement.service.book.ReaderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@WebMvcTest(ReaderService.class)
public class ReaderServiceTest {

    @MockBean
    private ReaderRepository repository;

    @Autowired
    private  ReaderService readerService;

    private ReaderRequest mockReaderRequest() {
        return ReaderRequest.of("vu","vu27082002@gmail.com", "0347106883");
    }

    private Reader mockReader() {
        return new Reader("1", "vu","vu27082002@gmail.com", "0347106883");
    }

    @Test
    public void testCreate_WhenEmailReaderAlreadyExist_ReturnThrowException() {
        ReaderRequest mockRequest = mockReaderRequest();
        Mockito.when(repository.checkExistEmail(mockRequest.getEmail())).thenReturn(true);
        Assertions.assertThrows(EmailAlreadyExistException.class, ()-> readerService.create(mockRequest));
    }

    @Test
    public  void testCreate_WhenPhoneNumberAlreadyExist_ReturnThrowException() {
        ReaderRequest mockRequest = mockReaderRequest();
        Mockito.when(repository.checkExistPhoneNumber(mockRequest.getPhoneNumber())).thenReturn(true);
        Assertions.assertThrows(PhoneNumberAlreadyExistException.class, ()-> readerService.create(mockRequest));
    }

    
}

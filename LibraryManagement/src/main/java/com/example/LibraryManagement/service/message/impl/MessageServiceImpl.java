package com.example.LibraryManagement.service.message.impl;

import com.example.LibraryManagement.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;


@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {
    private final MessageSource messageSource;
    @Override
    public String getMessage(String code, String language) {
        try {
            return messageSource.getMessage(code, null, new Locale(language));
        } catch (Exception ex) {
            return code;
        }
    }
}

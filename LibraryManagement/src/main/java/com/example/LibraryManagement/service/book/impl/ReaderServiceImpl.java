package com.example.LibraryManagement.service.book.impl;

import com.example.LibraryManagement.dto.base.PageResponse;
import com.example.LibraryManagement.dto.request.ReaderRequest;
import com.example.LibraryManagement.dto.response.ReaderResponse;
import com.example.LibraryManagement.entity.book.Reader;
import com.example.LibraryManagement.exception.book.EmailAlreadyExistException;
import com.example.LibraryManagement.exception.book.PhoneNumberAlreadyExistException;
import com.example.LibraryManagement.exception.book.ReaderAlreadyExistException;
import com.example.LibraryManagement.exception.book.ReaderNotFoundException;
import com.example.LibraryManagement.repository.book.ReaderRepository;
import com.example.LibraryManagement.service.book.ReaderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository repository;


    @Override
    @Transactional
    public ReaderResponse create(ReaderRequest request) {
        log.info("(create) request : {}", request);
        this.checkExistEmail(request.getEmail());
        this.checkExistPhoneNumber(request.getPhoneNumber());
        Reader reader = new Reader(
                request.getName(),
                request.getEmail(),
                request.getPhoneNumber()
        );
        repository.save(reader);
        return new ReaderResponse(reader.getId(), reader.getName(), reader.getEmail(), reader.getPhoneNumber());
    }

    @Override
    public PageResponse<ReaderResponse> list(String keyword, int size, int page, boolean isAll) {
        log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}", keyword, size, page, isAll);
        Page<ReaderResponse> responses = isAll ? repository.findAllCategory(PageRequest.of(page,size))
                : repository.search(PageRequest.of(page,size), keyword);

        return PageResponse.of(responses.getContent(), responses.getNumberOfElements());
    }

    @Override
    public ReaderResponse detail(String id) {
        log.info("(detail) id : {}", id);
        this.find(id);
        return repository.detail(id);
    }

    @Override
    @Transactional
    public void delete(String id) {
        log.info("(delete) id : {}", id);
        this.find(id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public ReaderResponse update(String id, ReaderRequest request) {
        log.info("(update) id : {}, request : {}", id, request);
        Reader reader = find(id);
        this.checkForUpdate(reader.getEmail(), reader.getPhoneNumber(), request.getEmail(), request.getPhoneNumber());
        log.debug("check name of reader already exists when update");
        setValueUpDate(reader, request);
        repository.save(reader);
        return new ReaderResponse(reader.getId(), reader.getName(), reader.getEmail(), reader.getPhoneNumber());
    }

    private void setValueUpDate(Reader reader, ReaderRequest request) {
        log.info("(setValueUpdate)");
        reader.setName(request.getName());
        reader.setEmail(request.getEmail());
        reader.setPhoneNumber(request.getPhoneNumber());
    }

    private void checkForUpdate(String email, String phoneNumber, String emailRequest, String phoneNumberRequest) {
        log.debug("checkNameForUpdate() email : {}, phoneNumber : {}, emailRequest : {}, phoneNumberRequest {}"
                , email, phoneNumber, emailRequest, phoneNumberRequest);
        if (!email.equals(emailRequest)) {
            this.checkExistEmail(emailRequest);
        }
        if (!phoneNumber.equals(phoneNumberRequest)) {
            this.checkExistPhoneNumber(phoneNumberRequest);
        }
    }

    private Reader find(String id) {
        log.debug("(find) id : {}", id);
        Reader reader = repository.findById(id).orElseThrow(ReaderNotFoundException::new);
        if(reader.isDeleted()) {
            throw  new ReaderNotFoundException();
        }
        return reader;
    }


    private void checkExistEmail(String email) {
        log.debug("(checkExistEmail) email : {}", email);
        repository.checkExistEmail(email);
        if (repository.checkExistEmail(email)) {
            log.error("(checkExistEmail) email : {}", email);
            throw new EmailAlreadyExistException();
        }
    }

    private void checkExistPhoneNumber(String phoneNumber) {
        log.debug("(checkExistPhoneNumber) phoneNumber : {}", phoneNumber);
        repository.checkExistPhoneNumber(phoneNumber);
        if (repository.checkExistPhoneNumber( phoneNumber)) {
            log.error("(checkExistPhoneNumber)  phoneNumber : {}", phoneNumber);
            throw new PhoneNumberAlreadyExistException();
        }
    }



}

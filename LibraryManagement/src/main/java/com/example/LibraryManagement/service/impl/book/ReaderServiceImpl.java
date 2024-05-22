package com.example.LibraryManagement.service.impl.book;

import com.example.LibraryManagement.dto.base.PageResponse;
import com.example.LibraryManagement.dto.request.ReaderRequest;
import com.example.LibraryManagement.dto.response.ReaderResponse;
import com.example.LibraryManagement.entity.book.Reader;
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
        this.checkExist(request.getName());
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

    private Reader find(String id) {
        log.debug("(find) id : {}", id);
        Reader reader = repository.findById(id).orElseThrow(ReaderNotFoundException::new);
        if(reader.isDeleted()) {
            throw  new ReaderNotFoundException();
        }
        return reader;
    }

    private void checkExist(String name) {
        log.debug("(checkExist) name : {}", name);
        repository.checkExist(name);
        if (repository.checkExist(name)) {
            log.error("checkExist:{}", name);
            throw new ReaderAlreadyExistException();
        }
    }
}

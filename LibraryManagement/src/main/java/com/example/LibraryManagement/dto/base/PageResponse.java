package com.example.LibraryManagement.dto.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Data
public class PageResponse<T> {
    private List<T> list;
    private int amount;

    public static <T> PageResponse<T> of(List<T> data, Integer amount) {
        return  new PageResponse<>(data, Objects.nonNull(amount) ? 0 : amount.intValue());
    }
}

package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Genre {
    private long id;
    private String name;

    public Genre(long id) {
        this.id = id;
    }

    public Genre(String name) {
        this.name = name;
    }
}

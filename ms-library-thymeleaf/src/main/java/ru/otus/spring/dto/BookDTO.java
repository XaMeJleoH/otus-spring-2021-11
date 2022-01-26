package ru.otus.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.model.Book;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String id;
    private String name;

    public static BookDTO toDto(Book book) {
        return new BookDTO(book.getId(), book.getName());
    }

    public static Book toDomainObject(BookDTO dto) {
        return new Book(dto.getId(), dto.getName());
    }
}

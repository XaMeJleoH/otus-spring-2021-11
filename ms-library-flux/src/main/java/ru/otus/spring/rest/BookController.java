package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.model.Book;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.rest.dto.BookDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book/")
public class BookController {
    private final BookRepository bookRepository;

    @GetMapping("/")
    public Flux<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .map(BookDTO::toDto);
    }

    @GetMapping("/{id}")
    public Mono<BookDTO> getBook(@PathVariable("id") String id) {
        return bookRepository.findById(id).map(BookDTO::toDto);
    }

    @PostMapping("/")
    public Mono<BookDTO> saveBook(@RequestBody Mono<BookDTO> bookDTO) {
        Mono<Book> book = bookDTO.map(BookDTO::toDomainObject);
        return bookRepository.save(book).map(BookDTO::toDto);
    }

    @PutMapping("/")
    public void updateBook(@RequestBody Mono<BookDTO> bookDTO) {
        Mono<Book> book = bookDTO.map(BookDTO::toDomainObject);
        bookRepository.save(book);
    }


}

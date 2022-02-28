package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Mono<BookDTO> saveBook(@RequestParam("bookName") String bookName) {
        return bookRepository.save(new Book(bookName)).map(BookDTO::toDto);
    }

    @PutMapping("/{id}")
    public Mono<BookDTO> updateBook(@PathVariable("id") String id, @RequestParam("bookName") String bookName) {
        return bookRepository.save(new Book(id, bookName)).map(BookDTO::toDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> removeBook(@PathVariable("id") String id) {
        return bookRepository.deleteById(id);
    }

}

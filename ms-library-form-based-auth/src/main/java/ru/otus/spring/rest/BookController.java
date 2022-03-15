package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.exception.LibraryException;
import ru.otus.spring.rest.dto.BookDTO;
import ru.otus.spring.service.LibraryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book/")
public class BookController {
    private final LibraryService libraryService;

    @GetMapping("/")
    public List<BookDTO> getAllBooks() {
        return libraryService.findAllBook().stream()
                .map(BookDTO::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable("id") String id) throws LibraryException {
        return BookDTO.toDto(libraryService.findBook(id));
    }

    @PostMapping("/")
    public void saveBook(@RequestParam("bookName") String bookName) {
        libraryService.saveBook(BookDTO.toDomainObject(new BookDTO(bookName)));
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable("id") String id, @RequestParam("bookName") String bookName) throws LibraryException {
        libraryService.updateBookName(id, bookName);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") String id) throws LibraryException {
        libraryService.deleteBook(id);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handle(LibraryException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Error error = new Error(String.valueOf(httpStatus.value()), e);
        return ResponseEntity.status(httpStatus).contentType(MediaType.APPLICATION_JSON).body(error);
    }


}

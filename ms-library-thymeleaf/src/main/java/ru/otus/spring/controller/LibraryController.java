package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.dto.BookDTO;
import ru.otus.spring.exception.LibraryException;
import ru.otus.spring.model.Book;
import ru.otus.spring.service.LibraryService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;

    @GetMapping("/")
    public String listPage(Model model) {
        List<BookDTO> bookDTOList = libraryService.findAllBook().stream()
                .map(BookDTO::toDto)
                .collect(Collectors.toList());
        model.addAttribute("books", bookDTOList);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") String id, Model model) throws LibraryException {
        Book book = libraryService.findBook(id);
        model.addAttribute("book", BookDTO.toDto(book));
        return "edit";
    }

    @PostMapping("/edit")
    public String updateBook(@ModelAttribute("book") BookDTO bookDTO) {
        libraryService.saveBook(BookDTO.toDomainObject(bookDTO));
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam("id") String id) throws LibraryException {
        libraryService.deleteBook(id);
        return "redirect:/";
    }

    @PostMapping("/save")
    public String saveBook(@RequestParam("bookName") String bookName) {
        libraryService.saveBook(BookDTO.toDomainObject(new BookDTO(bookName)));
        return "redirect:/";
    }

    @ExceptionHandler
    public ResponseEntity<Error> handle(LibraryException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Error error = new Error(String.valueOf(httpStatus.value()), e);
        return ResponseEntity.status(httpStatus).contentType(MediaType.APPLICATION_JSON).body(error);
    }
}

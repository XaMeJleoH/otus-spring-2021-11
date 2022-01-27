package ru.otus.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.otus.spring.dto.BookDTO;
import ru.otus.spring.model.Book;
import ru.otus.spring.service.impl.LibraryServiceImpl;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LibraryController.class, excludeAutoConfiguration = MongoAutoConfiguration.class)
@ComponentScan({"ru.otus.spring.controller"})
@ActiveProfiles("without-mongo")
class LibraryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private LibraryServiceImpl libraryService;

    @Test
    @SneakyThrows
    void listPage() {
        List<Book> bookList = List.of(new Book(getRandomUUID(), "Book1"), new Book(getRandomUUID(), "Book2"));
        given(libraryService.showAllBook()).willReturn(bookList);

        MvcResult mvcResult = mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();
        String htmlString = mvcResult.getResponse().getContentAsString();
        System.out.println(htmlString);
        assertTrue(htmlString.contains(bookList.get(0).getId()));
        assertTrue(htmlString.contains(bookList.get(0).getName()));
        assertTrue(htmlString.contains(bookList.get(1).getId()));
        assertTrue(htmlString.contains(bookList.get(1).getName()));
        assertTrue(htmlString.contains("Edit"));
        assertTrue(htmlString.contains("Delete"));
    }

    private String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

    @Test
    @SneakyThrows
    void editPage() {
        Book book = new Book(getRandomUUID(), "Book1");
        given(libraryService.findBook(book.getId())).willReturn(book);
        MvcResult mvcResult = mvc.perform(get("/edit?id=" + book.getId()))
                .andExpect(status().isOk())
                .andReturn();
        String htmlString = mvcResult.getResponse().getContentAsString();
        System.out.println(htmlString);
        assertTrue(htmlString.contains(book.getId()));
        assertTrue(htmlString.contains("<button type=\"submit\">Save</button>"));
    }

    @Test
    @SneakyThrows
    void saveBook() {
        Book book = new Book(getRandomUUID(), "Book1");
        String bookString = mapper.writeValueAsString(BookDTO.toDto(book));
        mvc.perform(post("/edit")
                        .contentType(APPLICATION_JSON)
                        .content(bookString))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @SneakyThrows
    void deleteBook() {
        Book book = new Book(getRandomUUID(), "Book1");
        given(libraryService.findBook(book.getId())).willReturn(book);
        String bookString = mapper.writeValueAsString(BookDTO.toDto(book));
        mvc.perform(post("/delete?id=" + book.getId())
                        .contentType(APPLICATION_JSON)
                        .content(bookString))
                .andExpect(status().is3xxRedirection());
    }
}
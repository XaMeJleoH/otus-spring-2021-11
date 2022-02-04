package ru.otus.spring.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.otus.spring.model.Book;
import ru.otus.spring.rest.dto.BookDTO;
import ru.otus.spring.service.LibraryService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@ContextConfiguration(classes = BookController.class)
@DisplayName("Тест контроллера")
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private LibraryService libraryService;

    @Test
    @SneakyThrows
    void getAllBooks() {
        List<Book> bookList = List.of(new Book(getRandomUUID(), "Book1"), new Book(getRandomUUID(), "Book2"));
        List<BookDTO> bookDTOList = bookList.stream()
                .map(BookDTO::toDto)
                .collect(Collectors.toList());
        given(libraryService.findAllBook()).willReturn(bookList);

        MvcResult mvcResult = mockMvc.perform(
                        get("/book/"))
                .andExpect(status().isOk())
                .andReturn();
        List<BookDTO> returnedList = mapper.readValue(mvcResult.getResponse().getContentAsString(),
                mapper.getTypeFactory().constructCollectionType(List.class, BookDTO.class));
        assertEquals(returnedList, bookDTOList);
    }

    private String getRandomUUID() {
        return UUID.randomUUID().toString();
    }
}
package ru.otus.spring.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("Тест контроллера")
class BookControllerTest {
    private MockMvc mockMvc;

    @Mock
    private LibraryService libraryService;

    @InjectMocks
    private BookController controller;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @SneakyThrows
    void getCompanions() {
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

    @Test
    void getAllBooks() {
    }
}
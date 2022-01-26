package ru.otus.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.config.SpringDataMongoV3Context;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.otus.spring.dto.BookDTO;
import ru.otus.spring.model.Book;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.service.LibraryService;
import ru.otus.spring.service.impl.LibraryServiceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LibraryControllerTest {

    private MockMvc mvc;

    private ObjectMapper mapper;

    @InjectMocks
    private LibraryController libraryController;

    @Mock
    private LibraryServiceImpl libraryService;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(libraryController).build();
        mapper = new ObjectMapper();
    }

    @Test
    @SneakyThrows
    void listPage() {
        List<Book> bookList = List.of(new Book(getRandomUUID(), "Book1"), new Book(getRandomUUID(), "Book2"));
        List<BookDTO> expectedResult = bookList.stream()
                .map(BookDTO::toDto).collect(Collectors.toList());
        given(libraryService.showAllBook()).willReturn(bookList);
/*
       mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));*/
        MvcResult mvcResult = mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)))
                .andReturn();
        String string = "s";
        String s = mapper.readValue(mvcResult.getResponse().getContentAsString(),
                String.class);
    }

    private String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

    @Test
    void editPage() {
    }

    @Test
    void saveBook() {
    }

    @Test
    void deleteBook() {
    }
}
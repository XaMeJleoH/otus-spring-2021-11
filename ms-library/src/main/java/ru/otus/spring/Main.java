package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.BookGenreDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.AuthorDTO;
import ru.otus.spring.dto.BookDTO;
import ru.otus.spring.dto.GenreDTO;
import ru.otus.spring.repository.BookRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:application.yml")
@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Main.class);

        AuthorDao authorDao = context.getBean(AuthorDao.class);
        BookDao bookDao = context.getBean(BookDao.class);
        GenreDao genreDao = context.getBean(GenreDao.class);
        BookGenreDao bookGenreDao = context.getBean(BookGenreDao.class);

        System.out.println("All count " + authorDao.count());

        //dao.insert(new Author(2, "ivan"));
        Author author = new Author(9, "ivan");
        authorDao.insert(author);
        bookDao.insert(new Book("lord of the ring", author.getId()));
        System.out.println(bookDao.getAll());
        System.out.println(authorDao.getAll());


        BookRepository bookRepository = context.getBean(BookRepository.class);
        System.out.println(bookRepository.getBookById(1));
        System.out.println(bookRepository.getAllBook());
        AuthorDTO authorDto1 = new AuthorDTO("Rolling");
        List<GenreDTO> genreDTOList = new ArrayList<>();
        genreDTOList.add(new GenreDTO("story"));
        genreDTOList.add(new GenreDTO("detective"));
        genreDTOList.add(new GenreDTO("fantasy"));
        BookDTO bookDTO = new BookDTO("harry potter", authorDto1, genreDTOList);
        bookRepository.insertBook(bookDTO);
        System.out.println(bookRepository.getAllBook());
        System.out.println(genreDao.getAll());
        System.out.println(bookGenreDao.getAll());
        try {
            Console.main(args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

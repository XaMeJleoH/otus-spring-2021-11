package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.sql.SQLException;

@PropertySource("classpath:application.yml")
@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Main.class);

        AuthorDao dao = context.getBean(AuthorDao.class);
        BookDao bookDao = context.getBean(BookDao.class);

        System.out.println("All count " + dao.count());

        //dao.insert(new Author(2, "ivan"));
        Author author = new Author(9,"ivan");
        dao.insert(author);
        bookDao.insert(new Book("lord of the ring", author));
        System.out.println(bookDao.getAll());
        try {
            Console.main(args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

package ru.otus.spring;

import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class TestHelper {

    public static final String HARRY = "HARRY";
    public static final String DONTZOVA = "Dontzova";
    public static final String STORY = "Story";
    public static final String FANTASY = "Fantasy";
    public static final String COMMENT1 = "Amazing book";
    public static final String COMMENT = "Tanya Grotter is better when this...";
    public static final String COMMENT2 = "PERFECTO!";
    public static final String ROY = "ROY";

    public static Book createBook() {
        List<Author> authorList = new ArrayList<>();
        authorList.add(createAuthor(DONTZOVA));
        authorList.add(createAuthor(ROY));
        List<Genre> genreList = createGenreList();
        List<Comment> commentList = createCommentList();
        return new Book(HARRY, authorList, genreList);
    }

    private static List<Comment> createCommentList() {
        List<Comment> commentList = new ArrayList<>();
        commentList.add(createComment(COMMENT));
        commentList.add(createComment(COMMENT1));
        return commentList;
    }

    private static List<Genre> createGenreList() {
        List<Genre> genreList = new ArrayList<>();
        genreList.add(createGenre(STORY));
        genreList.add(createGenre(FANTASY));
        return genreList;
    }

    private static Comment createComment(String comment) {
        return new Comment(comment);
    }

    private static Genre createGenre(String genre) {
        return new Genre(genre);
    }

    private static Author createAuthor(String author) {
        return new Author(author);
    }
}

package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
@NamedEntityGraph(name = "library-book-entity-graph",
        attributeNodes = {@NamedAttributeNode("author")})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne(targetEntity = Author.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ToString.Exclude
    private List<Genre> genreList;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Comment> commentList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return id > 0 && Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Book(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public Book(Long id, List<Genre> genreList) {
        this.id = id;
        this.genreList = genreList;
    }

    public Book(String name, Author author, List<Genre> genreList, List<Comment> commentList) {
        this.name = name;
        this.author = author;
        this.genreList = genreList;
        this.commentList = commentList;
    }
}

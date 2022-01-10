package ru.otus.spring.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.model.Book;
import ru.otus.spring.repository.BookRepository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    @Transactional
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    @Transactional
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    @Transactional
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("library-book-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.genreList join fetch b.commentList", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }
}

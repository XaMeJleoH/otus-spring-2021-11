package ru.otus.spring.service.migrate.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.jpa.Author;
import ru.otus.spring.repository.mongo.AuthorRepository;
import ru.otus.spring.service.migrate.MigrateAuthorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MigrateAuthorServiceImpl implements MigrateAuthorService {
    private final AuthorRepository mongoAuthorRepository;
    private final ru.otus.spring.repository.jpa.AuthorRepository jpaAuthorRepository;

    @Override
    public void migrate() {
        List<Author> authorList = jpaAuthorRepository.findAll();
        mongoAuthorRepository.saveAll(getMongoAuthorList(authorList));
    }

    private List<ru.otus.spring.model.mongo.Author> getMongoAuthorList(List<ru.otus.spring.model.jpa.Author> authorList) {
        return authorList.stream().map(this::getAuthor).collect(Collectors.toUnmodifiableList());
    }

    private ru.otus.spring.model.mongo.Author getAuthor(ru.otus.spring.model.jpa.Author author) {
        return new ru.otus.spring.model.mongo.Author(author.getName());
    }
}

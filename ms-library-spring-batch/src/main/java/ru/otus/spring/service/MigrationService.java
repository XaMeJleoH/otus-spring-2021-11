package ru.otus.spring.service;

public interface MigrationService {
    void migrateAuthor();

    void migrateGenre();

    void migrateBook();

    void migrateComment();
}

package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.MigrationService;
import ru.otus.spring.service.migrate.MigrateAuthorService;
import ru.otus.spring.service.migrate.MigrateBookService;
import ru.otus.spring.service.migrate.MigrateCommentService;
import ru.otus.spring.service.migrate.MigrateGenreService;

@RequiredArgsConstructor
@Service
public class MigrationJPAtoMongoServiceImpl implements MigrationService {
    private final MigrateAuthorService migrateAuthorService;
    private final MigrateGenreService migrateGenreService;
    private final MigrateBookService migrateBookService;
    private final MigrateCommentService migrateCommentService;

    @Override
    public void migrateLibrary() {
        migrateAuthorService.migrate();
        migrateGenreService.migrate();
        migrateBookService.migrate();
        migrateCommentService.migrate();
    }
}

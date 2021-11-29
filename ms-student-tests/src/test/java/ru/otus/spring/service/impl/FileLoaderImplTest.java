package ru.otus.spring.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.model.StudentTestException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FileLoaderImplTest {

    @Mock
    private FileLoaderImpl fileLoader;

    @Test
    @DisplayName("Получение файла")
    void loadFile() {
        assertDoesNotThrow(() -> fileLoader.loadFile("gdf"));
    }
}
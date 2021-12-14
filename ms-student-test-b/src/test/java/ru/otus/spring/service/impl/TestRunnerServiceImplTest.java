package ru.otus.spring.service.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.spring.exception.StudentTestException;
import ru.otus.spring.model.User;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.TestExecutionService;
import ru.otus.spring.shell.event.publisher.TestEvent;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("Проверка раннера с тестированием: ")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@ComponentScan("ru.otus.spring.service")
@ExtendWith(MockitoExtension.class)
class TestRunnerServiceImplTest {

    @Spy
    private TestExecutionService testExecutionService;

    @Spy
    private IOService ioService;

    @InjectMocks
    private TestRunnerServiceImpl service;

    private TestEvent testEvent;

    @BeforeEach
    void setUp() {
        testEvent = new TestEvent(new Object(), new User("test", "test"));
    }

    @Test
    @SneakyThrows
    @DisplayName("Успешное тестирование")
    void run() {
        assertDoesNotThrow(() -> service.run(testEvent));
        verify(ioService).print(anyString(), any(), any());
        verify(ioService, times(0)).printFormat(anyString(), any());
        verify(testExecutionService).test(any());
    }

    @Test
    @SneakyThrows
    @DisplayName("Ошибка во время тестирования")
    void runWithException() {
        doThrow(StudentTestException.class).when(testExecutionService).test(testEvent.getUser());
        assertDoesNotThrow(() -> service.run(testEvent));
        verify(ioService).print(anyString(), any(), any());
        verify(ioService, times(1)).printFormat(anyString(), any());
        verify(testExecutionService).test(any());
    }
}
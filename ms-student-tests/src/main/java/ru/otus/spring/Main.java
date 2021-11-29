package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.model.StudentTestException;
import ru.otus.spring.service.TestExecutionService;
import ru.otus.spring.service.impl.ConsoleCustomPrintServiceImpl;

@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        TestExecutionService service = context.getBean(TestExecutionService.class);
        ConsoleCustomPrintServiceImpl printService = context.getBean(ConsoleCustomPrintServiceImpl.class);
        try {
            service.test();
        } catch (StudentTestException e) {
            printService.print("Something happened. Error: " + e.getMessage());
        }
        context.close();
    }
}

package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.model.QuestionsReadingException;
import ru.otus.spring.service.CustomPrintService;
import ru.otus.spring.service.TestExecutionService;
import ru.otus.spring.service.impl.ConsoleCustomPrintServiceImpl;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:/spring-context.xml");
        TestExecutionService service = context.getBean(TestExecutionService.class);
        ConsoleCustomPrintServiceImpl printService = context.getBean(ConsoleCustomPrintServiceImpl.class);
        try {
            service.test();
        } catch (QuestionsReadingException e) {
            printService.print("Something happened. Error: " + e.getMessage());
        }
        context.close();
    }
}

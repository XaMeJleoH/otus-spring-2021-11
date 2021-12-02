package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.service.TestRunnerService;

@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        TestRunnerService service = context.getBean(TestRunnerService.class);
        service.run();
        context.close();
    }
}

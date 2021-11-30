package ru.otus.spring.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.TestValidationService;
import ru.otus.spring.service.impl.TestValidationServiceImpl;

@Configuration
public class TestValidationConfig {

    @Bean
    public TestValidationService testValidation(@Value("${test.passed.number.answer}") int passedNumber){
        return new TestValidationServiceImpl(passedNumber);
    }
}

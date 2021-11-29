package ru.otus.spring.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.TestValidation;
import ru.otus.spring.service.impl.TestValidationImpl;

@Configuration
public class TestValidationConfig {
    @Value("${test.passed.number.answer}")
    private int passedNumber;

    @Bean
    public TestValidation testValidation(){
        return new TestValidationImpl(passedNumber);
    }
}

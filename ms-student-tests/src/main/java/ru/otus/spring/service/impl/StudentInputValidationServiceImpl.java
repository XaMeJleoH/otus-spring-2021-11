package ru.otus.spring.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.InputValidationService;

@Service
public class StudentInputValidationServiceImpl implements InputValidationService {
    @Override
    public boolean checkAnswer(String correctAnswer, String clientAnswer) {
        return StringUtils.equalsIgnoreCase(correctAnswer, clientAnswer);
    }
}

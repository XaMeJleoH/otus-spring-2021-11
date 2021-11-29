package ru.otus.spring.model;

import lombok.*;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    private String lastName;
    private String firstName;
    private List<Question> questionList;
    private int correctAnswer = 0;
    private int totalQuestion;

    public Test(List<Question> questionList) {
        this.questionList = questionList;
    }
}

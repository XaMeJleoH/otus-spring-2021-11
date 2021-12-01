package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    private List<Question> questionList;
    private int totalQuestion;

    public Test(List<Question> questionList) {
        this.questionList = questionList;
    }
}

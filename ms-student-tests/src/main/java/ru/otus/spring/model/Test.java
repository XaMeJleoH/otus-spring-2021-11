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
    private List<Question> questionList;
    private int totalQuestion;

    public Test(List<Question> questionList) {
        this.questionList = questionList;
    }
}

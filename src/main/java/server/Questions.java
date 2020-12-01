package server;

import java.io.Serializable;

/**
 * Created by Patrik Melander
 * Date: 2020-11-12
 * Time: 13:21
 * Project: Quiskampen
 * Copyright: MIT
 */
public class Questions implements Serializable {
    private final String question;
    private final String correctAnswer;
    private final String wrongAnswer1;
    private final String wrongAnswer2;
    private final String wrongAnswer3;
    private final String categoryName;


    public Questions(String question, String correctAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3, String categoryName) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
        this.categoryName = categoryName;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public String getCategoryName(){
        return categoryName;
    }
}

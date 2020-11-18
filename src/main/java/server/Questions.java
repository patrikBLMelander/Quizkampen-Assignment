package server.Categories;

import java.io.Serializable;

/**
 * Created by Patrik Melander
 * Changed by Axel Reje
 * Date: 2020-11-14
 * Time: 13:21
 * Project: Quiskampen
 * Copyright: MIT
 */
public class Questions implements Serializable {
    private String category;
    private String question;
    private String correctAnswer;
    private String wrongAnswer1;
    private String wrongAnswer2;
    private String wrongAnswer3;


    public Questions(String question, String correctAnswer, String wrongAnswer1, String wrongAnswer2,
                     String wrongAnswer3) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
    }
    
    Questions(String category, String question, String correctAnswer, String wrongAnswer1, String wrongAnswer2,
                     String wrongAnswer3)
    {
        this.category = category;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
        
    }
    
    
    public String getCategory()
    {
        return category;
        
    }
    
    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getWrongAnswer0() {
        return wrongAnswer1;
    }

    public String getWrongAnswer1() {
        return wrongAnswer2;
    }

    public String getWrongAnswer2() {
        return wrongAnswer3;
    }
}

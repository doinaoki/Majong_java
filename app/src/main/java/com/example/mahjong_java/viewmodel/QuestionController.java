package com.example.mahjong_java.viewmodel;


public class QuestionController {
    private Question question;
    public QuestionController(){
    }

    public Question generate(){
        GenerateQuestion generateQuestion = new GenerateQuestion();
        question = generateQuestion.generate();
        return question;
    }

    public void storeQuestion(){
        return;
    }
}

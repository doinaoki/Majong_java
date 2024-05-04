package com.example.mahjong_java.viewmodel;


public class QuestionController {
    public QuestionController(){
    }

    public Question generate(){
        GenerateQuestion generateQuestion = new GenerateQuestion();
        generateQuestion.generate();
        return generateQuestion.getQuestion();
    }
}

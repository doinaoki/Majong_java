package com.example.mahjong_java.modelview;


public class QuestionController {
    public QuestionController(){
    }

    public Question generate(){
        Hand hand = new Hand();
        hand.create();
        GenerateQuestion generateQuestion = new GenerateQuestion(hand);
        generateQuestion.generate();
        return generateQuestion.getQuestion();
    }

}

package com.example.mahjong_java.modelview;

import java.util.Random;

public class Hand {
    private int[] pieces;
    public Hand(){

    }
    public int[] getHand () {
        return pieces;
    }
    public void create() {
        int numberOfHand = QuestionSetting.numberOfHand;
        int[] newPieces = new int[9];
        Random random = new Random();
        int nowNumberPieces = 0;

        while (nowNumberPieces < numberOfHand) {
            int piece = random.nextInt(9);
            if (newPieces[piece] < 4) {
                nowNumberPieces += 1;
                newPieces[piece] += 1;
            }
        }
        pieces = newPieces;
    }
}

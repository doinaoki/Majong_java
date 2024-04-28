package com.example.mahjong_java.modelview;

import java.util.Arrays;
import java.util.Random;

public class Hand {
    private int[] pieces;
    public Hand(){

    }
    public Hand(int[] pieces){
        this.pieces = pieces;
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
    public boolean addPiece(int piece, int number) {
        if (pieces[piece] > 4 - number) return false;
        pieces[piece] += number;
        return true;
    }

    public boolean subtractPiece(int piece, int number) {
        if (pieces[piece] < number) return false;
        pieces[piece] -= number;
        return true;
    }
    public boolean subtractTriplet(int piece) {
        if (pieces[piece] < 3) return false;
        pieces[piece] -= 3;
        return true;
    }
    public void addTriplet(int piece) {
        pieces[piece] += 3;
    }

    public boolean subtractSequence(int piece) {
        if (piece > 6) return false;
        if (pieces[piece] == 0 | pieces[piece + 1] == 0 | pieces[piece + 2] == 0) return false;
        pieces[piece] -= 1;
        pieces[piece + 1] -= 1;
        pieces[piece + 2] -= 1;
        return true;
    }
    public void addSequence(int piece) {
        pieces[piece] += 1;
        pieces[piece + 1] += 1;
        pieces[piece + 2] += 1;
    }

    public boolean isEmpty() {
        return Arrays.stream(pieces).sum() == 0;
    }

}

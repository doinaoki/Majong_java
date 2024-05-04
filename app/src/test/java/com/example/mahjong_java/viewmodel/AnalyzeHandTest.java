package com.example.mahjong_java.viewmodel;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalyzeHandTest {
    public List<int[]> piecesArray = new ArrayList<>();
    public List<Integer[]> readyAnswer = new ArrayList<>();
    public AnalyzeHandTest() {
        // not ready
        piecesArray.add(new int[]{4, 4, 0, 0, 0, 1, 0, 0, 4});
        readyAnswer.add(Arrays.stream(new int[]{}).boxed().toArray(Integer[]::new));

        //four sequences (ready is {6, 9})
        piecesArray.add(new int[]{1, 2, 2, 1, 0, 1, 2, 2, 2});
        readyAnswer.add(Arrays.stream(new int[]{5, 8}).boxed().toArray(Integer[]::new));


        //three sequences and one triplet (ready is {2, 5, 8})
        piecesArray.add(new int[]{3, 0, 1, 2, 2, 2, 1, 0, 2});
        readyAnswer.add(Arrays.stream(new int[]{1, 4, 7}).boxed().toArray(Integer[]::new));


        //two sequences and two triplets (ready is {6, 9})
        piecesArray.add(new int[]{3, 0, 3, 0, 1, 2, 2, 1, 1});
        readyAnswer.add(Arrays.stream(new int[]{5, 8}).boxed().toArray(Integer[]::new));


        //one sequences and three triplets (ready is {6, 9})
        piecesArray.add(new int[]{3, 0, 3, 0, 3, 0, 1, 1, 2});
        readyAnswer.add(Arrays.stream(new int[]{5, 8}).boxed().toArray(Integer[]::new));


        //four triplets (ready is {8, 9})
        piecesArray.add(new int[]{3, 0, 3, 0, 3, 0, 3, 0, 1});
        readyAnswer.add(Arrays.stream(new int[]{7, 8}).boxed().toArray(Integer[]::new));

        //seven pairs
        piecesArray.add(new int[]{2, 0, 2, 2, 2, 2, 0, 2, 1});
        readyAnswer.add(Arrays.stream(new int[]{8}).boxed().toArray(Integer[]::new));

    }

    @Test
    public void analyzedHand_isCorrect() {
        for (int i = 0; i < piecesArray.size(); i++) {
            Hand hand = new Hand(piecesArray.get(i));
            Integer[] answerWinPieces = readyAnswer.get(i);
            GenerateQuestion generateQuestion = new GenerateQuestion(hand);
            GenerateQuestion.AnalyzeHand analyzeHand = generateQuestion.new AnalyzeHand();
            analyzeHand.analyzeReady();
            List<GenerateQuestion.AnalyzedPieces> arrayHands = analyzeHand.getAnalyzedReady();
            List<Integer> analyzedWinPieces = new ArrayList<>();
            for (GenerateQuestion.AnalyzedPieces hands: arrayHands) {
                if (!analyzedWinPieces.contains(hands.getWinPieces())) {
                    analyzedWinPieces.add(hands.getWinPieces());
                }
            }
            Integer[] awp =  analyzedWinPieces.toArray(new Integer[0]);
            assertArrayEquals(answerWinPieces, awp);
        }
    }

}

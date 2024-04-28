package com.example.mahjong_java.modelview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class AnalyzeHandTest {
    public List<int[]> piecesArray = new ArrayList<>();
    public AnalyzeHandTest() {
        // not ready
        //piecesArray.add(new int[]{4, 4, 0, 0, 0, 1, 0, 0, 4});

        //four sequences (ready is {6, 9})
        //piecesArray.add(new int[]{1, 2, 2, 1, 0, 1, 2, 2, 2});

        //three sequences and one triplet (ready is {2, 5, 8})
        //piecesArray.add(new int[]{3, 0, 1, 2, 2, 2, 1, 0, 2});

        //two sequences and two triplets (ready is {6, 9})
        //piecesArray.add(new int[]{3, 0, 3, 0, 1, 2, 2, 1, 1});

        //one sequences and three triplets (ready is {6, 9})
        //piecesArray.add(new int[]{3, 0, 3, 0, 3, 0, 1, 1, 2});

        //four triplets (ready is {8, 9})
        //piecesArray.add(new int[]{3, 0, 3, 0, 3, 0, 3, 0, 1});
    }

    @Test
    public void analyzedHand_isCorrect() {
        for (int[] pieces: piecesArray) {
            Hand hand = new Hand(pieces);
            //int sumPieces = Arrays.stream(pieces).sum();
            GenerateQuestion generateQuestion = new GenerateQuestion(hand);
            GenerateQuestion.AnalyzeHand analyzeHand = generateQuestion.new AnalyzeHand();
            analyzeHand.analyzeReady();
            List<HashMap<Integer, List<Deque<int[]>>>> arrayHands = analyzeHand.getWinHands();
            for (HashMap<Integer, List<Deque<int[]>>> hands: arrayHands) {
                for (Integer head: hands.keySet()) {
                    List<Deque<int[]>> arrayElements = hands.get(head);
                    System.out.printf("head = %d\n", head);
                    assert arrayElements != null;
                    for (Deque<int[]> elements: arrayElements){
                        for (int[] el: elements) {
                            for (int e: el) {
                                System.out.print(e);
                            }
                            System.out.print(" ");
                        }
                        System.out.print("\n");
                    }
                }
            }
        }
    }

}

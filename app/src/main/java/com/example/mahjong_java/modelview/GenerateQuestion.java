package com.example.mahjong_java.modelview;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class GenerateQuestion {
    private Question question = new Question();

    private final Hand hand;
    public GenerateQuestion (Hand hand){
        this.hand = hand;
    }

    public Question getQuestion() {
        return question;
    }

    public void generate() {
        boolean readyHand = QuestionSetting.readyHand;
        question = new Question();
        AnalyzeHand analyzeHand = new AnalyzeHand();
        analyzeHand.analyzeReady();
        analyzeRoles();
    }

    class AnalyzeHand {
        private List<Deque<int[]>> possibleWinElements = new ArrayList<>();
        private List<HashMap<Integer, List<Deque<int[]>>>> possibleWinHand = new ArrayList<>();
        public List<HashMap<Integer, List<Deque<int[]>>>> getWinHands() {
            return possibleWinHand;
        }

        public void analyzeReady() {
            List<Integer> winPieces = new ArrayList<Integer>();
            for (int winPiece = 0; winPiece < 9; winPiece++) {
                if (!hand.addPiece(winPiece, 1)) {
                    continue;
                }
                if (!analyzeHead().isEmpty()) {
                    winPieces.add(winPiece);
                }
                hand.subtractPiece(winPiece, 1);
            }
            return;
        }

        private HashMap<Integer, List<Deque<int[]>>> analyzeHead() {
            HashMap<Integer, List<Deque<int[]>>> analyzedHand = new HashMap<>();
            for (int head = 0; head < 9; head++) {
                if (!hand.subtractPiece(head, 2)) {
                    continue;
                }
                possibleWinElements = new ArrayList<>();
                analyzeElements();
                if (!possibleWinElements.isEmpty()) {
                    analyzedHand.put(head, possibleWinElements);
                }
                hand.addPiece(head, 2);
            }
            possibleWinHand.add(analyzedHand);
            return analyzedHand;
        }

        private void analyzeElements() {
            Deque<int[]> elements = new ArrayDeque<>();
            analyzeElements0(elements, 0);
            return;
        }

        private void analyzeElements0(Deque<int[]> elements, int nextInt) {
            if (hand.isEmpty()) {
                possibleWinElements.add(elementsClone(elements));
                return;
            }
            for (int number = nextInt; number < 9; number++) {
                if (hand.subtractTriplet(number)) {
                    elements.push(new int[]{number, number, number});
                    analyzeElements0(elements, number);
                    hand.addTriplet(number);
                    elements.pop();
                }
                if (hand.subtractSequence(number)) {
                    elements.push(new int[]{number, number + 1, number + 2});
                    analyzeElements0(elements, number);
                    hand.addSequence(number);
                    elements.pop();
                }
            }
        }

        private Deque<int[]> elementsClone(Deque<int[]> elements) {
            Deque<int[]> newElements = new ArrayDeque<>();
            for (int[] element : elements) {
                int[] newElement = Arrays.copyOf(element, element.length);
                newElements.push(newElement);
            }
            return newElements;
        }
    }

    private void analyzeRoles() {

    }
}

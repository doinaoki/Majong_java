package com.example.mahjong_java.viewmodel;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
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
        private List<AnalyzedPieces> arrayAnalyzedPieces;
        private final List<AnalyzedPieces> arrayAnalyzedReady = new ArrayList<>();
        public List<AnalyzedPieces> getAnalyzedReady() {
            return arrayAnalyzedReady;
        }

        public void analyzeReady() {
            List<AnalyzedPieces> winPieces = new ArrayList<>();
            for (int winPiece = 0; winPiece < 9; winPiece++) {
                if (!hand.addPiece(winPiece, 1)) {
                    continue;
                }
                for (AnalyzedPieces ap: analyzeHead()) {
                    ap.setWinPiece(winPiece);
                    arrayAnalyzedReady.add(ap);
                }
                hand.subtractPiece(winPiece, 1);
            }
        }

        private List<AnalyzedPieces> analyzeHead() {
            List<AnalyzedPieces> analyzedPieces = new ArrayList<>();
            for (int head = 0; head < 9; head++) {
                if (!hand.subtractPiece(head, 2)) {
                    continue;
                }
                arrayAnalyzedPieces = new ArrayList<>();
                analyzeElements();
                for(AnalyzedPieces ah: arrayAnalyzedPieces) {
                    ah.setHead(head);
                    analyzedPieces.add(ah);
                }
                hand.addPiece(head, 2);
            }
            return analyzedPieces;
        }

        private void analyzeElements() {
            Deque<int[]> elements = new ArrayDeque<>();
            analyzeElements0(elements, 0);
        }

        private void analyzeElements0(Deque<int[]> elements, int nextInt) {
            if (hand.isEmpty()) {
                AnalyzedPieces analyzedPieces = new AnalyzedPieces();
                analyzedPieces.setElements(elements);
                arrayAnalyzedPieces.add(analyzedPieces);
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

    class AnalyzedPieces {
        private Deque<int[]> elements;
        private Integer head;
        private Integer winPiece;

        public void setElements(Deque<int[]> elements) {
            this.elements = elements;
        }

        public void setHead(Integer head) {
            this.head = head;
        }

        public void setWinPiece(Integer winPieces) {
            this.winPiece = winPieces;
        }

        public Integer getWinPieces() {
            return winPiece;
        }
    }

    private void analyzeRoles() {

    }
}

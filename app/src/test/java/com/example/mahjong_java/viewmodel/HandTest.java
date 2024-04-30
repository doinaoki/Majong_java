package com.example.mahjong_java.viewmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class HandTest {
    @Test
    public void numberOfHand_isCorrect() {
        Hand hand = new Hand();
        hand.create();
        int[] pieces = hand.getHand();
        int sumPieces = Arrays.stream(pieces).sum();
        assertEquals(sumPieces, QuestionSetting.numberOfHand);
        for (int piece: pieces) {
            assertTrue(piece <= 4);
        }
    }
}
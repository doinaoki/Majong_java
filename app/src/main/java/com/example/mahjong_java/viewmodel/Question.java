package com.example.mahjong_java.viewmodel;

import java.util.HashMap;
import java.util.List;

public class Question {
    private Hand hand;
    private List<Integer> winPieces;
    private HashMap<Integer, List<String>> roles = null;

    public Hand getHand() {
        return hand;
    }

    public List<Integer> getWinPieces() {
        return winPieces;
    }

    public HashMap<Integer, List<String>> getRoles(){
        return roles;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void setWinPieces(List<Integer> winPieces) {
        this.winPieces = winPieces;
    }

    public void setRoles(HashMap<Integer, List<String>> roles) {
        this.roles = roles;
    }

}

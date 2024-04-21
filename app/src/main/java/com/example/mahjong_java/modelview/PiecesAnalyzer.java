package com.example.mahjong_java.modelview;

public class PiecesAnalyzer {
    private final Pieces pieces;
    public PiecesAnalyzer(Pieces pieces){
        this.pieces = pieces;
    }
    public Question analyze (){
        System.out.println(this.pieces);
        return null;
    }

}

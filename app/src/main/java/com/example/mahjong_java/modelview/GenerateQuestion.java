package com.example.mahjong_java.modelview;

public class GenerateQuestion {

    public GenerateQuestion (){

    }
    public Question generate() {
        Pieces pieces = new Pieces();
        PiecesAnalyzer piecesAnalyzer = new PiecesAnalyzer(pieces);
        return piecesAnalyzer.analyze();
    }

}

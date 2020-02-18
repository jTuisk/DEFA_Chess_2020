package com.engine;

public enum PieceType {
    KING("K", "KING.png"), QUEEN("Q", "QUEEN.png"), KNIGHT("N", "KNIGHT.png"),
    BISHOP("B", "BISHOP.png"), ROOK("R", "ROOK.png"), PAWN("P", "PAWN.png");

    private String pieceName;
    private String imgFile;

    PieceType(String pieceName, String imgFile){
        this.pieceName = pieceName;
        this.imgFile = imgFile;
    }

    public String getImgFileString(){
        return this.imgFile;
    }

    @Override
    public String toString(){
        return this.pieceName;
    }
}

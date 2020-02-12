package com.engine;

public enum PieceType {
    KING("K"), QUEEN("Q"), KNIGHT("N"), BISHOP("B"), ROOK("R"), PAWN("P");

    private String pieceName;

    PieceType(String pieceName){
        this.pieceName = pieceName;
    }

    @Override
    public String toString(){
        return this.pieceName;
    }
}

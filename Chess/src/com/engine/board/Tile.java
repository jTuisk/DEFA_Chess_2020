package com.engine.board;

import com.engine.piece.Piece;

import java.util.Arrays;

public class Tile {

    protected final int[] tilePos;

    private Piece piece;

    public Tile(int[] tilePos){
        this.tilePos = tilePos;
        this.piece = null;
    }

    public Tile(int[] tilePos, Piece piece){
        this.tilePos = tilePos;
        this.piece = piece;
    }


    public void setPiece(Piece piece){
        this.piece = piece;
    }


    public boolean isEmpty(){
        return this.piece == null;
    }

    public Piece getPiece(){
        return this.piece;
    }

    @Override
    public String toString(){
        if(this.piece == null)
            return "-";
        return getPiece().getAlliance().isWhite() ? getPiece().toString().toLowerCase() : getPiece().toString();
    }
}


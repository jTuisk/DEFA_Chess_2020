package com.engine.board;

import com.engine.piece.Piece;

import java.util.Arrays;

public class Tile {

    protected final int[] tileCoords;

    private Piece piece;

    public Tile(int[] tileCoords){
        this.tileCoords = tileCoords;
        this.piece = null;
    }

    public Tile(int[] tileCoords, Piece piece){
        this.tileCoords = tileCoords;
        this.piece = piece;
    }


    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public int[] getTileCoords(){
        return this.tileCoords;
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


package com.engine.board;

import com.engine.piece.Piece;

public class Tile {

    protected final int[] tileCoords;

    private final Piece piece;

    public Tile(int[] tileCoords){
        this.tileCoords = tileCoords;
        this.piece = null;
    }

    public Tile(int[] tileCoords, Piece piece){
        this.tileCoords = tileCoords;
        this.piece = piece;
    }

    public int[] getTileCoords(){
        return this.tileCoords;
    }

    public boolean isTileEmpty(){
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


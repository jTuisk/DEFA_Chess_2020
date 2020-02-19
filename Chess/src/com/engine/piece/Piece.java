package com.engine.piece;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Piece {

    protected final Alliance alliance;
    protected final PieceType pieceType;
    protected int[] piecePosition;

    Piece(Alliance alliance, int[] piecePosition, PieceType pieceType){
        this.alliance = alliance;
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
    }

    public static boolean canMove(List<Move> moves, Move destinationMove){
        for(Move move : moves){
            System.out.println(move+" = "+destinationMove+" == "+move.equals(destinationMove));
            if(move.equals(destinationMove))
                return true;
        }
        return false;
    }

    public abstract List<Move> getAllAvailableMoves(Board board);

    public void setPiecePosition(int[] piecePosition){
        this.piecePosition = piecePosition;
    }

    public int[] getPosition(){
        return this.piecePosition;
    }

    public Alliance getAlliance(){
        return this.alliance;
    }

    public PieceType getPieceType(){
        return this.pieceType;
    }

    @Override
    public String toString(){
        return pieceType.toString();
    }

    public static class Move {

        final Board board;
        final Piece piece;
        final int[] destCoords;
        final Piece attackedPiece;

        public Move(Board board, Piece piece, int[] destCoords){
            this.board = board;
            this.piece = piece;
            this.destCoords = destCoords;
            this.attackedPiece = board.getTile(destCoords).getPiece();
        }

        @Override
        public boolean equals(Object o){
            if(!(o instanceof  Move))
                return false;
            Move move = (Move) o;
            return this.destCoords[0] == ((Move) o).destCoords[0] && this.destCoords[1] == ((Move) o).destCoords[1];
        }

        @Override
        public String toString(){
            return piece+": "+ Arrays.toString(destCoords);
        }
    }
}

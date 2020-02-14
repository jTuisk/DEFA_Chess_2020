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
    protected final int[] piecePosition;
    protected final PieceType pieceType;

    Piece(Alliance alliance, int[] piecePosition, PieceType pieceType){
        this.alliance = alliance;
        this.piecePosition = piecePosition;
        this.pieceType = pieceType;
    }

    public boolean canMove(List<Move> moves, Move destinationMove){
        for(Move move : moves){
            if(move == destinationMove)
                return true;
        }
        return false;
    }

    public abstract List<Move> getAllAvailableMoves(Board board);

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

    public class Move {

        final Board board;
        final Piece piece;
        final Piece attackedPiece;
        final int[] destCoords;

        public Move(Board board, Piece piece, int[] destCoords){
            this.board = board;
            this.piece = piece;
            this.destCoords = destCoords;
            this.attackedPiece = board.getTile(destCoords).getPiece();
        }

        @Override
        public String toString(){
            return piece+": "+ Arrays.toString(destCoords);
        }
    }
}

package com.engine.piece;

import com.engine.Alliance;
import com.engine.PieceType;
import com.engine.board.Board;
import com.engine.player.Player;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    //Castling - https://simple.wikipedia.org/wiki/Castling

    public King(Board board, Alliance alliance, Player player, int[] piecePosition) {
        super(board, alliance, player, piecePosition, PieceType.KING);
    }
    public King(Board board, Alliance alliance, Player player, int[] piecePosition, boolean firstMove) {
        super(board, alliance, player, piecePosition, PieceType.KING);
        this.firstMove = firstMove;
    }

    @Override
    public List<Move> getAllAvailableMoves(){
        ArrayList<Move> moves = new ArrayList<>();

        for(Move move : this.getDiagonalMovesRightDown(1)){
            if(canMove(move.getDestCoords()))
                moves.add(move);
        }
        for(Move move : this.getDiagonalMovesLeftDown(1)){
            if(canMove(move.getDestCoords()))
                moves.add(move);
        }
        for(Move move : this.getDiagonalMovesLeftUp(1)){
            if(canMove(move.getDestCoords()))
                moves.add(move);
        }
        for(Move move : this.getDiagonalMovesRightUp(1)){
            if(canMove(move.getDestCoords()))
                moves.add(move);
        }
        for(Move move : this.getVerticalMovesUp(1)){
            if(canMove(move.getDestCoords()))
                moves.add(move);
        }
        for(Move move : this.getVerticalMovesDown(1)){
            if(canMove(move.getDestCoords()))
                moves.add(move);
        }
        for(Move move : this.getHorizontalMovesLeft(1)){
            if(canMove(move.getDestCoords()))
                moves.add(move);
        }
        for(Move move : this.getHorizontalMovesRight(1)){
            if(canMove(move.getDestCoords()))
                moves.add(move);
        }
        return moves;
    }
}

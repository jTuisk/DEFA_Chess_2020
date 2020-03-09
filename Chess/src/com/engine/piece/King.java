package com.engine.piece;

import com.engine.Alliance;
import com.engine.GameUtils;
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

    @Override
    public List<Move> getAllAvailableMoves(){
        ArrayList<Move> moves = new ArrayList<>();

        for(Move move : this.getDiagonalMovesRightDown(1)){
                moves.add(move);
        }
        for(Move move : this.getDiagonalMovesLeftDown(1)){
                moves.add(move);
        }
        for(Move move : this.getDiagonalMovesLeftUp(1)){
                moves.add(move);
        }
        for(Move move : this.getDiagonalMovesRightUp(1)){
                moves.add(move);
        }
        for(Move move : this.getVerticalMovesUp(1)){
                moves.add(move);
        }
        for(Move move : this.getVerticalMovesDown(1)){
                moves.add(move);
        }
        for(Move move : this.getHorizontalMovesLeft(1)){
                moves.add(move);
        }
        for(Move move : this.getHorizontalMovesRight(1)){
                moves.add(move);
        }
        return moves;
    }
}

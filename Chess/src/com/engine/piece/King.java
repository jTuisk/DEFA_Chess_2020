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

    public King(Alliance alliance, Player player, int[] piecePosition) {
        super(alliance, player, piecePosition, PieceType.KING);
    }

    @Override
    public List<Move> getAllAvailableMoves(Board board){
        ArrayList<Move> moves = new ArrayList<>();

        for(Move move : this.getDiagonalMovesRightDown(board, 1)){
                moves.add(move);
        }
        for(Move move : this.getDiagonalMovesLeftDown(board, 1)){
                moves.add(move);
        }
        for(Move move : this.getDiagonalMovesLeftUp(board, 1)){
                moves.add(move);
        }
        for(Move move : this.getDiagonalMovesRightUp(board, 1)){
                moves.add(move);
        }
        for(Move move : this.getVerticalMovesUp(board, 1)){
                moves.add(move);
        }
        for(Move move : this.getVerticalMovesDown(board, 1)){
                moves.add(move);
        }
        for(Move move : this.getHorizontalMovesLeft(board, 1)){
                moves.add(move);
        }
        for(Move move : this.getHorizontalMovesRight(board, 1)){
                moves.add(move);
        }
        return moves;
    }
}

package com.engine.piece;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{

    public Bishop(Alliance alliance, int[] piecePosition) {
        super(alliance, piecePosition, PieceType.BISHOP);
    }

    @Override
    public List<Move> getAllAvailableMoves(Board board){
        ArrayList<Move> moves = new ArrayList<>();

        for(Move move : this.getDiagonalMovesRightDown(board, GameUtils.GAME_BOARD_SIZE_HEIGHT)){
            moves.add(move);
        }
        for(Move move : this.getDiagonalMovesLeftDown(board, GameUtils.GAME_BOARD_SIZE_HEIGHT)){
            moves.add(move);
        }
        for(Move move : this.getDiagonalMovesLeftUp(board, GameUtils.GAME_BOARD_SIZE_HEIGHT)){
            moves.add(move);
        }
        for(Move move : this.getDiagonalMovesRightUp(board, GameUtils.GAME_BOARD_SIZE_HEIGHT)){
            moves.add(move);
        }
        return moves;
    }
}

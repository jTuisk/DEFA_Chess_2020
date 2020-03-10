package com.engine.piece;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;
import com.engine.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{

    public Bishop(Board board, Alliance alliance, Player player, int[] piecePosition) {
        super(board, alliance, player, piecePosition, PieceType.BISHOP);
    }

    @Override
    public List<Move> getAllAvailableMoves(){
        ArrayList<Move> moves = new ArrayList<>();

        for(Move move : this.getDiagonalMovesRightDown(GameUtils.GAME_BOARD_SIZE_HEIGHT)){
            if(canMove(move.getDestCoords()))
                moves.add(move);
        }
        for(Move move : this.getDiagonalMovesLeftDown(GameUtils.GAME_BOARD_SIZE_HEIGHT)){
            if(canMove(move.getDestCoords()))
                moves.add(move);
        }
        for(Move move : this.getDiagonalMovesLeftUp(GameUtils.GAME_BOARD_SIZE_HEIGHT)){
            if(canMove(move.getDestCoords()))
                moves.add(move);
        }
        for(Move move : this.getDiagonalMovesRightUp(GameUtils.GAME_BOARD_SIZE_HEIGHT)){
            if(canMove(move.getDestCoords()))
                moves.add(move);
        }
        return moves;
    }
}

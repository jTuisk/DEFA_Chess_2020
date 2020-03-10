package com.engine.piece;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;
import com.engine.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Board board, Alliance alliance, Player player, int[] piecePosition) {
        super(board, alliance, player, piecePosition, PieceType.QUEEN);
    }

    @Override
    public List<Move> getAllAvailableMoves(){
        ArrayList<Move> moves = new ArrayList<>();

        for(Move move : this.getDiagonalMovesRightDown(GameUtils.GAME_BOARD_SIZE_HEIGHT)){
            if(canMove(move.getDestCoords()))
                moves.add(move);
        }
        for(Move move : this.getDiagonalMovesLeftDown( GameUtils.GAME_BOARD_SIZE_HEIGHT)){
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
        for(Move move : this.getVerticalMovesUp(GameUtils.GAME_BOARD_SIZE_HEIGHT)){
            if(canMove(move.getDestCoords()))
                moves.add(move);
        }
        for(Move move : this.getVerticalMovesDown(GameUtils.GAME_BOARD_SIZE_HEIGHT)){
            if(canMove(move.getDestCoords()))
                moves.add(move);
        }
        for(Move move : this.getHorizontalMovesLeft(GameUtils.GAME_BOARD_SIZE_WIDTH)){
            if(canMove(move.getDestCoords()))
                moves.add(move);
        }
        for(Move move : this.getHorizontalMovesRight(GameUtils.GAME_BOARD_SIZE_WIDTH)){
            if(canMove(move.getDestCoords()))
                moves.add(move);
        }
        return moves;
    }
}
package com.engine.piece;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;
import com.engine.player.Player;

import java.util.ArrayList;
import java.util.List;


public class Rook extends Piece{

    //Castling - https://simple.wikipedia.org/wiki/Castling

    public Rook(Alliance alliance, Player player, int[] piecePosition) {
        super(alliance, player, piecePosition, PieceType.ROOK);
    }

    @Override
    public List<Move> getAllAvailableMoves(Board board){
        ArrayList<Move> moves = new ArrayList<>();

        for(Move move : this.getVerticalMovesUp(board, GameUtils.GAME_BOARD_SIZE_HEIGHT)){
            moves.add(move);
        }
        for(Move move : this.getVerticalMovesDown(board, GameUtils.GAME_BOARD_SIZE_HEIGHT)){
            moves.add(move);
        }
        for(Move move : this.getHorizontalMovesLeft(board, GameUtils.GAME_BOARD_SIZE_WIDTH)){
            moves.add(move);
        }
        for(Move move : this.getHorizontalMovesRight(board, GameUtils.GAME_BOARD_SIZE_WIDTH)){
            moves.add(move);
        }
        return moves;
    }
}

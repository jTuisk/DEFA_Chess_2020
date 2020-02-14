package com.engine.piece;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    //Castling - https://simple.wikipedia.org/wiki/Castling
    private final int[] startPos;

    public King(Alliance alliance, int[] piecePosition) {
        super(alliance, piecePosition, PieceType.KING);
        this.startPos = piecePosition;
    }

    public List<Move> getAllAvailableMoves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
            for(int y = 0; y < GameUtils.GAME_BOARD_SIZE_WIDTH; y++){
                int piece_x = Math.abs(this.getPosition()[0] - x);
                int piece_y  = Math.abs(this.getPosition()[1] - y);

                //Can't move to under enemy fire.
                if(piece_x*piece_y != 1)
                    continue;

                Piece pieceOnDestinationTile = board.getTile(new int[] {x,y}).getPiece();

                if(pieceOnDestinationTile != null && pieceOnDestinationTile.getAlliance() == this.alliance)
                    continue;

                 moves.add(new Move(board, this, new int[]{x,y}));
            }
        }
        return moves;
    }
}

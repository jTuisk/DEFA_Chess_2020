package com.engine.piece;

import com.engine.Alliance;
import com.engine.PieceType;
import com.engine.board.Board;

import java.util.List;

public class Bishop extends Piece{

    public Bishop(Alliance alliance, int[] piecePosition) {
        super(alliance, piecePosition, PieceType.BISHOP);
    }

    //TODO:
    //Check moving path. Can't jump over pieces.
    @Override
    public boolean canMove(Board board, int[] destinationCoords) {
        if(board.getTile(destinationCoords).getPiece().getAlliance() == this.getAlliance())
            return false;

        int x = Math.abs(this.getPosition()[0] - destinationCoords[0]);
        int y = Math.abs(this.getPosition()[1] - destinationCoords[1]);

        return x==y;
    }
}

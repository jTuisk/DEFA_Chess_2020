package com.engine.piece;

import com.engine.Alliance;
import com.engine.PieceType;
import com.engine.board.Board;

import java.util.List;

public class King extends Piece {

    public King(Alliance alliance, int[] piecePosition) {
        super(alliance, piecePosition, PieceType.KING);
    }

    //TODO:
    //Can't move to under enemy fire.
    @Override
    public boolean canMove(Board board, int[] destinationCoords) {
        if(board.getTile(destinationCoords).getPiece().getAlliance() == this.getAlliance())
            return false;

        int x = Math.abs(this.getPosition()[0] - destinationCoords[0]);
        int y = Math.abs(this.getPosition()[1] - destinationCoords[1]);

        return x*y == 1;
    }
}

package com.engine.piece;

import com.engine.Alliance;
import com.engine.PieceType;
import com.engine.board.Board;

public class King extends Piece {

    public King(Alliance alliance, int[] piecePosition) {
        super(alliance, piecePosition, PieceType.KING);
    }

    //TODO:
    //Can't move to under enemy fire.
    //Castling - https://simple.wikipedia.org/wiki/Castling
    @Override
    public boolean canMove(Board board, int[] destinationCoords) {
        if(board.getTile(destinationCoords).getPiece() != null && board.getTile(destinationCoords).getPiece().getAlliance() == this.getAlliance())
            return false;

        int x = Math.abs(this.getPosition()[0] - destinationCoords[0]);
        int y = Math.abs(this.getPosition()[1] - destinationCoords[1]);

        return x*y == 1;
    }
}
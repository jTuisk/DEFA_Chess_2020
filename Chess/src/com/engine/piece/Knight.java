package com.engine.piece;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;

import java.util.List;

public class Knight extends Piece {

    public Knight(Alliance alliance, int[] piecePosition) {
        super(alliance, piecePosition, PieceType.KNIGHT);
    }


    @Override
    public boolean canMove(Board board, int[] destinationCoords) {
        if(!GameUtils.coordsInGameBoard(destinationCoords))
            return false;

        if(board.getTile(destinationCoords).getPiece() != null && board.getTile(destinationCoords).getPiece().getAlliance() == this.getAlliance())
            return false;

        int x = Math.abs(this.getPosition()[0] - destinationCoords[0]);
        int y = Math.abs(this.getPosition()[1] - destinationCoords[1]);

        return x*y == 2;
    }
}

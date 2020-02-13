package com.engine.piece;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;

public class Bishop extends Piece{

    public Bishop(Alliance alliance, int[] piecePosition) {
        super(alliance, piecePosition, PieceType.BISHOP);
    }

    @Override
    public boolean canMove(Board board, int[] destinationCoords) {
        if(board.getTile(destinationCoords).getPiece() != null && board.getTile(destinationCoords).getPiece().getAlliance() == this.getAlliance())
            return false;

        int x = Math.abs(this.getPosition()[0] - destinationCoords[0]);
        int y = Math.abs(this.getPosition()[1] - destinationCoords[1]);

        for(int i = 1; i < x && x == y; i++) {
            int[] tempPos = new int[]{this.getPosition()[0] + i, this.getPosition()[1] + 1};
            if (GameUtils.coordsInGameBoard(tempPos) && board.getTile(tempPos).getPiece() != null)
                return false;
        }
        return x==y;
    }
}

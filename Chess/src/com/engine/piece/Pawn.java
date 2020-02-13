package com.engine.piece;


import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;
import com.engine.board.Tile;

import java.util.Arrays;

public class Pawn extends Piece {

    private boolean firstMove;

    public Pawn(Alliance alliance, int[] piecePosition) {
        super(alliance, piecePosition, PieceType.PAWN);
        firstMove = true;
    }
    // PUUTTUU -> https://en.wikipedia.org/wiki/En_passant
    @Override
    public boolean canMove(Board board, int[] destinationCoords) {
        if(!GameUtils.coordsInGameBoard(destinationCoords))
            return false;

        Tile destinationTile = board.getTile((destinationCoords));

        int x = Math.abs(this.getPosition()[0] - destinationCoords[0]);
        int y = Math.abs(this.getPosition()[1] - destinationCoords[1]);

        if(x == 1 && y == 1 && destinationTile.getPiece() != null && destinationTile.getPiece().getAlliance() != this.getAlliance())
            return true;

        if(destinationTile.getPiece() != null)
            return false;

        for(int i = 1; i < x; i++){
            int[] tempPos = new int[] {this.getPosition()[0]+i, this.getPosition()[1]};
            if(GameUtils.coordsInGameBoard(tempPos) && board.getTile(tempPos).getPiece() != null)
                return false;
        }

        return firstMove ? (x == 2 || x == 1) && y == 0 : x == 1 && y == 0;
    }
}

package com.engine.piece;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;

import java.util.Arrays;
import java.util.List;


public class Rook extends Piece{

    public Rook(Alliance alliance, int[] piecePosition) {
        super(alliance, piecePosition, PieceType.ROOK);
    }

    //TODO:
    //Check moving path. Can't jump over pieces.
    @Override
    public boolean canMove(Board board, int[] destinationCoords) {
        if(board.getTile(destinationCoords).getPiece() != null && board.getTile(destinationCoords).getPiece().getAlliance() == this.getAlliance())
            return false;

        int x = Math.abs(this.getPosition()[0] - destinationCoords[0]);
        int y = Math.abs(this.getPosition()[1] - destinationCoords[1]);

        if(x == 0 || y == 0){
            //System.out.println(x+":"+y);
            for(int i = 1;  (i < x) || (i < y); i++){
                int[] tempPos = x != 0 ?
                        new int[] {this.getPosition()[0]+i, this.getPosition()[1]} :
                        new int[] {this.getPosition()[0], this.getPosition()[1]+i};
                //System.out.println(Arrays.toString(tempPos) + " - "+(GameUtils.coordsInGameBoard(tempPos) && board.getTile(tempPos).getPiece() != null));
                if(GameUtils.coordsInGameBoard(tempPos) && board.getTile(tempPos).getPiece() != null)
                    return false;
            }
        }
        return (x != 0 && y == 0) || (x == 0 && y != 0);
    }
}

package com.engine.piece;


import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;
import com.engine.board.Tile;

public class Pawn extends Piece {

    private boolean firstMove;

    public Pawn(Alliance alliance, int[] piecePosition) {
        super(alliance, piecePosition, PieceType.PAWN);
        firstMove = true;
    }

    //TODO:
    //Check moving path. Can't jump over pieces.
    @Override
    public boolean canMove(Board board, int[] destinationCoords) {
        if(!GameUtils.coordsInGameBoard(destinationCoords))
            return false;

        Tile destinationTile = board.getTile((destinationCoords));

        if(destinationTile.getPiece() != null && destinationTile.getPiece().getAlliance() == this.getAlliance())
            return false;

        int x = Math.abs(this.getPosition()[0] - destinationCoords[0]);
        int y = Math.abs(this.getPosition()[1] - destinationCoords[1]);

        /*
        for(int i = x; i < 0; i--){
            for(int j = y; j <= 0; j--){
                System.out.println((destinationCoords[0]-i)+" : "+(destinationCoords[1]-j));
                if(board.getTile(new int[]{destinationCoords[0]-i, destinationCoords[1]-j}).getPiece() != null){
                    return false;
                }
            }
        }*/

        if(x+y == 2 && destinationTile.getPiece() != null && destinationTile.getPiece().getAlliance() != this.getAlliance())
            return true;

        return firstMove ? (x == 2 || x == 1) && y == 0: x == 1 && y == 0;
    }
}

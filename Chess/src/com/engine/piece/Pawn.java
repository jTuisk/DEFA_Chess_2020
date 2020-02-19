package com.engine.piece;


import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;
import com.engine.board.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Alliance alliance, int[] piecePosition) {
        super(alliance, piecePosition, PieceType.PAWN);
    }

    @Override
    public List<Move> getAllAvailableMoves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
            for(int y = 0; y < GameUtils.GAME_BOARD_SIZE_WIDTH; y++){
                int piece_x = Math.abs(this.getPosition()[0] - x);
                int piece_y  = Math.abs(this.getPosition()[1] - y);

                Piece pieceOnDestinationTile = board.getTile(new int[] {x,y}).getPiece();

                if(piece_x == 1 && piece_y == 1){
                    if(pieceOnDestinationTile != null && pieceOnDestinationTile.getAlliance() == this.alliance)
                        continue;
                }

                if(firstMove ? !((piece_x == 2 || piece_x == 1) && piece_y == 0) : !(piece_x == 1 && piece_y == 0))
                    continue;

                if(pieceOnDestinationTile != null && pieceOnDestinationTile.getAlliance() == this.alliance)
                    continue;

                moves.add(new Move(board, this, new int[]{x,y}));
            }
        }
        return moves;
    }
}

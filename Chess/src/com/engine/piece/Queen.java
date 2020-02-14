package com.engine.piece;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Alliance alliance, int[] piecePosition) {
        super(alliance, piecePosition, PieceType.QUEEN);
    }
/*
    @Override
    public List<Move> getAllAvailableMoves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++) {
            for (int y = 0; y < GameUtils.GAME_BOARD_SIZE_WIDTH; y++) {
                int piece_x = Math.abs(this.getPosition()[0] - x);
                int piece_y = Math.abs(this.getPosition()[1] - y);

                if ((piece_x != piece_y) && (piece_x != 0 && piece_y != 0))
                    continue;

                Piece pieceOnDestinationTile = board.getTile(new int[]{x, y}).getPiece();

                if (pieceOnDestinationTile != null && pieceOnDestinationTile.getAlliance() == this.alliance)
                    continue;

                if(pieceOnDestinationTile != null){
                    boolean reachAble = true;
                    if((piece_x != piece_y)){ // diagonally
                        for(int i = 1; i < piece_x; i++){
                            int[] tempCoords = new int[] {x-i, y-i};
                            if(GameUtils.coordsInGameBoard(tempCoords) && board.getTile(tempCoords).getPiece() != null)
                                reachAble = false;
                        }
                    }else{ // horizontally or vertically
                        for(int i = 1; (i < piece_x) || (i < piece_y); i++){
                            int[] tempCoords = x != 0 ? new int[] {x-i, y} : new int[] {x, y-i};
                            if(GameUtils.coordsInGameBoard(tempCoords) && board.getTile(tempCoords).getPiece() != null)
                                reachAble = false;
                        }
                    }
                    if(reachAble){
                        moves.add(new Move(board, this, pieceOnDestinationTile.getPosition()));
                        continue;
                    }
                }else
                    moves.add(new Move(board, this, new int[]{x,y}));

            }
        }
        return moves;
    }
*/
    @Override
    public List<Move> getAllAvailableMoves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
            for(int y = 0; y < GameUtils.GAME_BOARD_SIZE_WIDTH; y++){
                int piece_x = Math.abs(this.getPosition()[0] - x);
                int piece_y  = Math.abs(this.getPosition()[1] - y);

                if((piece_x != 0 && piece_y != 0))
                    continue;

                Piece pieceOnDestinationTile = board.getTile(new int[] {x,y}).getPiece();

                if(pieceOnDestinationTile != null && pieceOnDestinationTile.getAlliance() == this.alliance)
                    continue;

                if(pieceOnDestinationTile != null){
                    boolean reachAble = true;
                    for(int i = 1; (i < piece_x) || (i < piece_y); i++){
                        int[] tempCoords = x != 0 ? new int[] {x-i, y} : new int[] {x, y-i};
                        if(GameUtils.coordsInGameBoard(tempCoords) && board.getTile(tempCoords).getPiece() != null)
                            reachAble = false;
                    }
                    if(reachAble){
                        moves.add(new Move(board, this, pieceOnDestinationTile.getPosition()));
                        continue;
                    }
                }else
                    moves.add(new Move(board, this, new int[]{x,y}));
            }
        }
        return moves;
    }
}
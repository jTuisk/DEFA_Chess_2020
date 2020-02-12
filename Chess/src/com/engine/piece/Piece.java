package com.engine.piece;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Piece {

    protected final Alliance alliance;
    protected final int[] piecePosition;
    protected final PieceType pieceType;

    Piece(Alliance alliance, int[] piecePosition, PieceType pieceType){
        this.alliance = alliance;
        this.piecePosition = piecePosition;
        this.pieceType = pieceType;
    }

    public abstract boolean canMove(Board board, int[] destinationCoords);

    public List<Move> getAvailableMoves(Board board){
        List<Move> availableMoves = new ArrayList<>();
        for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
            for(int y = 0; y < GameUtils.GAME_BOARD_SIZE_WIDTH; y++){
                if(canMove(board, new int[]{x,y})){
                    availableMoves.add(new Move(board, this, new int[]{x, y}));
                    String attack = board.getTile(new int[]{x, y}).getPiece() == null ? "" : " ? "+board.getTile(new int[]{x, y}).getPiece().toString();
                    System.out.println(Arrays.toString(this.getPosition())+" -> "+Arrays.toString(new int[]{x, y})+""+attack);
                }
            }
        }

        return availableMoves;
    }

    public int[] getPosition(){
        return this.piecePosition;
    }

    public Alliance getAlliance(){
        return this.alliance;
    }

    public PieceType getPieceType(){
        return this.pieceType;
    }

    @Override
    public String toString(){
        return pieceType.toString();
    }

    public class Move {

        final Board board;
        final Piece piece;
        final Piece attackedPiece;
        final int[] destCoords;

        public Move(Board board, Piece piece, int[] destCoords){
            this.board = board;
            this.piece = piece;
            this.destCoords = destCoords;
            this.attackedPiece = board.getTile(destCoords).getPiece();
        }

        @Override
        public String toString(){
            return piece+": "+ Arrays.toString(destCoords);
        }
    }
}

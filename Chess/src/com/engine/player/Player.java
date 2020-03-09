package com.engine.player;

import com.engine.Alliance;
import com.engine.GameStatus;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;
import com.engine.piece.Piece;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private final Board board;
    private final Alliance alliance;
    private List<Piece> lostPieces;
    private Player enemyPlayer;

    public Player(Board board, Alliance alliance){
        this.board = board;
        this.lostPieces = new ArrayList<>();
        this.alliance = alliance;
    }

    public Player getEnemyPlayer(){return this.enemyPlayer;}
    public void setEnemyPlayer(Player enemyPlayer){this.enemyPlayer = enemyPlayer;}

    public boolean kingUnderAttack() {
        for (Piece.Move move : enemyPlayer.getAllMoves()) {
            Piece piece = this.board.getTile(move.getDestCoords()).getPiece();
            if (piece == null)
                continue;

            if (piece.getPieceType() == PieceType.KING && piece.getAlliance() != this.alliance) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Piece.Move> getAllMoves(){
        ArrayList<Piece.Move> moves = new ArrayList<>();

        for(Piece piece : this.board.getPiecesOnBoard()){
            if(piece.getAlliance() == this.alliance)
                moves.addAll(piece.getAllAvailableMoves());
        }

        return moves;
    }


    public Alliance getAlliance() {
        return this.alliance;
    }

    public List<Piece> getLostPieces(){
        return this.lostPieces;
    }

    public void clearLostPieces(){ this.lostPieces = new ArrayList<>();}

    public void addLostPiece(Piece piece){
        this.lostPieces.add(piece);
    }

    @Override
    public String toString(){
        return ""+this.alliance;
    }
}

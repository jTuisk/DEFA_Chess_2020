package com.engine.player;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.Time;
import com.engine.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final Alliance alliance;
    private Time time;
    private boolean didMove;
    private List<Piece> lostPieces;

    public Player(Alliance alliance, Time time){
        this.lostPieces = new ArrayList<>();
        this.alliance = alliance;
        this.time = time;
        didMove = false;
    }

    public Alliance getAlliance() {
        return this.alliance;
    }

    public boolean isKingAlive(){
        for(Piece piece : GameUtils.PIECES_ONBOARD){
            if(piece.getAlliance() == this.alliance && piece.getPieceType() == PieceType.KING)
                return true;
        }
        return false;
    }

    public Time getTime(){
        return this.time;
    }

    public List<Piece> getLostPieces(){
        return this.lostPieces;
    }

    public void addLostPiece(Piece piece){
        this.lostPieces.add(piece);
    }

    @Override
    public String toString(){
        return ""+this.alliance;
    }
}

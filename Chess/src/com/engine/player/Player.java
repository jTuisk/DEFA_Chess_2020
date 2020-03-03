package com.engine.player;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final Alliance alliance;
    private boolean didMove;
    private List<Piece> lostPieces;

    public Player(Alliance alliance){
        this.lostPieces = new ArrayList<>();
        this.alliance = alliance;
        didMove = false;
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

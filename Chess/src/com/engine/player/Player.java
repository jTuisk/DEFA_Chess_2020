package com.engine.player;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.Time;
import com.engine.piece.Piece;

public class Player {

    private final Alliance alliance;
    private Time time;
    private boolean didMove;

    public Player(Alliance alliance, Time time){
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
}

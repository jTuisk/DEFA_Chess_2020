package com.engine.player;

import com.engine.Alliance;
import com.engine.Time;

public class Player {

    private final Alliance alliance;
    private Time time;

    public Player(Alliance alliance, Time time){
        this.alliance = alliance;
        this.time = time;
    }

    public Alliance getAlliance() {
        return this.alliance;
    }

    public Time getTime(){
        return this.time;
    }
}

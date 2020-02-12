package com.engine;

public class Time {

    private int milliseconds, seconds, minutes;

    public Time(int minutes, int seconds, int milliseconds){
        this.milliseconds = milliseconds;
        this.seconds = seconds;
        this.minutes = minutes;
    }


    @Override
    public String toString(){
        return this.minutes+":"+this.seconds;
    }
}

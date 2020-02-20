package com.engine;

public enum Alliance {
    BLACK, WHITE;

    public boolean isWhite(){
        return this == Alliance.WHITE;
    }

    public boolean isBlack(){
        return this == Alliance.BLACK;
    }

}

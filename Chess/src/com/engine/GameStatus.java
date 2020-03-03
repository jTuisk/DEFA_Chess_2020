package com.engine;

public enum GameStatus {
    PROMOTING_PAWN('P'), INITIALIZING_GAME('I'), PLAYER_TURN('T'), GAME_OVER('G');

    private char statusChar;

    GameStatus(char statusChar){
        this.statusChar = statusChar;
    }

    public static GameStatus getGameStatusByChar(char statusChar){
        switch(statusChar){
            case 'P':
                return PROMOTING_PAWN;
            case 'I':
                return INITIALIZING_GAME;
            case 'T':
                return PLAYER_TURN;
            case 'G':
                return GAME_OVER;
        }
        return null;
    }

    @Override
    public String toString(){
        return ""+this.statusChar;
    }
}

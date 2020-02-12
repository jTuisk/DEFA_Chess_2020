package com.engine;

public class GameUtils {

    /**
     *  PLAYER
     */
    public static final Time SPEED_CHESS_TIME = new Time(5,0,0);


    /**
     * GAME BOARD
     */
    public static final int GAME_BOARD_SIZE_WIDTH = 8;
    public static final int GAME_BOARD_SIZE_HEIGHT = 8;

    public static boolean coordsInGameBoard(int[] coords){
        if(coords == null || coords.length != 2)
            return false;
        return (coords[0] >= 0 && coords[0] < GAME_BOARD_SIZE_HEIGHT && coords[1] >= 0 && coords[1] < GAME_BOARD_SIZE_WIDTH);
    }

}

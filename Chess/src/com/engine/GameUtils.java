package com.engine;

import java.awt.*;

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

    /**
     * GAME GUI
     */
    public static final Dimension GUI_FRAME_SIZE = new Dimension(800, 650);
    public static final Dimension BOARD_FRAME_SIZE = new Dimension(600,650);
    public static final Dimension DATA_FRAME_SIZE = new Dimension(200,650);
    public static final Dimension SINGLE_TILE_SIZE = new Dimension(65,65);
    public static final Color BOARD_FRAME_COLOR = new Color(232, 232, 232);
    public static final Color DARK_TILE_COLOR = new Color(125, 135, 150);
    public static final Color LIGHT_TILE_COLOR = new Color(232, 235, 239);
    public static final Point GUI_FRAME_POSITION = new Point((Toolkit.getDefaultToolkit().getScreenSize().width/2) - (GUI_FRAME_SIZE.width/2),
                                                                    (Toolkit.getDefaultToolkit().getScreenSize().height/2) - (GUI_FRAME_SIZE.height/2));

    public static final String[] GAME_BOARD_VERTICAL_TILE_COORDINATES = new String[] { "1", "2", "3", "4", "5", "6", "7", "8"};
    public static final String[] GAME_BOARD_HORIZONTAL_TILE_COORDINATES = new String[] { "A", "B", "C", "D", "E", "F", "G", "H"};
}

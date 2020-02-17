package com.engine.ui;

import com.engine.GameUtils;

import javax.swing.*;
import java.awt.*;

public class GameBoard {

    GameBoard(){
        /*super.setLocation(10,10);
        super.setBackground(Color.BLACK);
        super.setSize(GameUtils.BOARD_FRAME_SIZE);
        for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
            for(int y = 0; y < GameUtils.GAME_BOARD_SIZE_WIDTH; y++){
                GameBoardTile tile = new GameBoardTile(new int[]{x, y});
                super.add(tile.getTile());
            }
        }
        super.validate();*/
    }

    public static JPanel setupGameBoard(){
        JPanel mainBoard = new JPanel(); // have numbers and charachers.
        JPanel chessBoard = new JPanel(); // chess board (tiles 8x8)
        JPanel data = new JPanel(); // have timer, who's turn, lost pieces ect..
        mainBoard.setSize(GameUtils.BOARD_FRAME_SIZE);
        mainBoard.setLocation(0,0);
        mainBoard.setBackground(Color.BLACK);
        //board.setLayout(new GridLayout(8,8));


        return mainBoard;
    }
}

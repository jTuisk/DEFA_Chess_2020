package com.engine.ui;

import com.engine.GameUtils;

import javax.swing.*;
import java.awt.*;


public class GameBoard {

    public static JPanel setupDataPanel(){
        JPanel dataPanel = new JPanel();
        dataPanel.setBounds(GameUtils.BOARD_FRAME_SIZE.width, 0, GameUtils.DATA_FRAME_SIZE.width, GameUtils.DATA_FRAME_SIZE.height);
        dataPanel.setBackground(Color.WHITE);
        return dataPanel;
    }

    public static JPanel setupGameBoard() {
        JPanel gameBoard = new JPanel();
        gameBoard.setBackground(GameUtils.BOARD_FRAME_COLOR);
        gameBoard.setBounds(0,0, GameUtils.BOARD_FRAME_SIZE.width, GameUtils.BOARD_FRAME_SIZE.height);
        gameBoard.setLayout(null);

        setupBoardCoordinateLables(gameBoard);

        for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
            for(int y = 0; y < GameUtils.GAME_BOARD_SIZE_WIDTH; y++){
                new BoardTile(gameBoard, new int[] {x, y});
            }
        }

        return gameBoard;
    }

    private static void setupBoardCoordinateLables(JPanel gameBoard){
        for(int i = 0; i < GameUtils.GAME_BOARD_HORIZONTAL_TILE_COORDINATES.length; i++){
            JLabel temp = new JLabel(GameUtils.GAME_BOARD_HORIZONTAL_TILE_COORDINATES[i]);
            int xPos = (GameUtils.SINGLE_TILE_SIZE.width*i) + (GameUtils.SINGLE_TILE_SIZE.width);
            int yPos = (GameUtils.SINGLE_TILE_SIZE.height/4)*-1;
            temp.setBounds(xPos, yPos, GameUtils.SINGLE_TILE_SIZE.width, GameUtils.SINGLE_TILE_SIZE.height);
            temp.setForeground(Color.BLACK);
            gameBoard.add(temp);
        }

        for(int i = 0; i < GameUtils.GAME_BOARD_VERTICAL_TILE_COORDINATES.length; i++){
            JLabel temp = new JLabel(GameUtils.GAME_BOARD_VERTICAL_TILE_COORDINATES[i]);
            int xPos = (GameUtils.SINGLE_TILE_SIZE.width/4);
            int yPos = (GameUtils.SINGLE_TILE_SIZE.height*i) + (GameUtils.SINGLE_TILE_SIZE.height)/2;
            temp.setBounds(xPos, yPos, GameUtils.SINGLE_TILE_SIZE.width, GameUtils.SINGLE_TILE_SIZE.height);
            temp.setForeground(Color.BLACK);
            gameBoard.add(temp);
        }
    }
    
    private static class BoardTile extends JPanel {
        JPanel tile;

         BoardTile(JPanel gameBoard, int[] pos){
            JPanel temp = new JPanel();
            int xPos = (GameUtils.SINGLE_TILE_SIZE.width*pos[0]) + (GameUtils.SINGLE_TILE_SIZE.width/2);
            int yPos = (GameUtils.SINGLE_TILE_SIZE.height*pos[1]) + (GameUtils.SINGLE_TILE_SIZE.height/2);
            temp.setBounds(xPos, yPos, GameUtils.SINGLE_TILE_SIZE.width, GameUtils.SINGLE_TILE_SIZE.height);
            temp.setBackground(getTileBackgroundColor(pos));
            gameBoard.add(temp);
        }

        private static Color getTileBackgroundColor(int[] pos){
            if((pos[0] + pos[1]) % 2 == 0)
                return GameUtils.DARK_TILE_COLOR;
            return GameUtils.LIGHT_TILE_COLOR;
        }
    }
}
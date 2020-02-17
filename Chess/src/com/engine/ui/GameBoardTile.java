package com.engine.ui;

import com.engine.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoardTile extends JPanel {

    JPanel tile;

    public GameBoardTile(int[] coords){
        this.tile = new JPanel();
        this.tile.setSize(GameUtils.SINGLE_TILE_SIZE);
        this.tile.setLocation(getTilePosition(coords));
        this.tile.setBackground(getTileColor(coords));
        this.tile.validate();
    }

    public JPanel getTile(){
        return this.tile;
    }

    private Color getTileColor(int[] coords){
        if((coords[0] + coords[1]) % 2 == 0)
            return GameUtils.DARK_TILE_COLOR;
        return GameUtils.LIGHT_TILE_COLOR;
    }

    private Point getTilePosition(int[] coords){
        return new Point((coords[0]*GameUtils.GAME_BOARD_SIZE_HEIGHT), (coords[1]*GameUtils.GAME_BOARD_SIZE_WIDTH));
    }
}

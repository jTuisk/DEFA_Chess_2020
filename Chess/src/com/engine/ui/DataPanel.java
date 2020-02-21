package com.engine.ui;

import com.engine.GameUtils;
import com.engine.board.Board;
import com.engine.player.Player;

import javax.swing.*;

public class DataPanel extends JPanel{

    private Board board;
    private Player p1;
    private Player p2;

    public DataPanel(Board board, Player p1, Player p2){
        super();
        this.board = board;
        this.p1 = p1;
        this.p2 = p2;
        setupDataPanel();
    }

    private void setupDataPanel(){
        super.setLayout(null);
        super.setBounds(GameUtils.BOARD_FRAME_SIZE.width, 0, GameUtils.DATA_FRAME_SIZE.width, GameUtils.DATA_FRAME_SIZE.height);
        super.setBackground(GameUtils.BOARD_FRAME_COLOR);
        super.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, GameUtils.SELECTED_TILE_COLOR));

        JPanel playerTurnPanel = new JPanel();
        playerTurnPanel.setBounds(GameUtils.DATA_FRAME_SIZE.width/10, GameUtils.DATA_FRAME_SIZE.width/10, 150, 50);
        playerTurnPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, GameUtils.SELECTED_TILE_COLOR));
        playerTurnPanel.setBackground(GameUtils.BOARD_FRAME_COLOR);

        //JPanel whitePlayerData = playerData(board, p1, 100, "Player: White");
        //JPanel blackPlayerData = playerData(board, p2, 320, "Player: Black");

        super.add(playerTurnPanel);
        //dataPanel.add(whitePlayerData);
        //dataPanel.add(blackPlayerData);
    }

    public void refreshDataPanel(){

    }

    private static JPanel playerData(Board board, Player player, int posY, String headerText){
        JPanel playerData = new JPanel();
        playerData.setBounds(GameUtils.DATA_FRAME_SIZE.width/10, posY, 150, 200);
        playerData.setBorder(BorderFactory.createMatteBorder(1,1,1,1, GameUtils.SELECTED_TILE_COLOR));
        playerData.setBackground(GameUtils.BOARD_FRAME_COLOR);

        JLabel header = new JLabel(headerText);
        header.setBounds(10, 10, (GameUtils.DATA_FRAME_SIZE.width/4*2), 10);

        JPanel lostPieces = new JPanel();
        lostPieces.setLayout(null);
        lostPieces.setBounds(10, 10, 130, 100);
        lostPieces.setBorder(BorderFactory.createMatteBorder(1,1,1,1, GameUtils.SELECTED_TILE_COLOR));
        lostPieces.setBackground(GameUtils.BOARD_FRAME_COLOR);

        playerData.add(lostPieces);
        playerData.add(header);
        playerData.setLayout(null);
        return playerData;
    }
}

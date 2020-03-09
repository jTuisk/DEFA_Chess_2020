package com.engine.ui;

import com.engine.FileManager;
import com.engine.GameUtils;
import com.engine.board.Board;
import com.engine.piece.Piece;
import com.engine.player.Player;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UserInterface extends JFrame {

    GameBoardPanel gameBoardPanel;
    DataPanel dataPanel;
    Board board;
    Player p1;
    Player p2;


    public UserInterface(Board board, Player p1, Player p2, boolean visible){
        super("DEFA Chess 2020, Made by Janek Tuisk");
        if(!visible)
            return;

        this.board = board;
        this.p1 = p1;
        this.p2 = p2;
        this.gameBoardPanel = new GameBoardPanel(board);
        this.dataPanel = new DataPanel(board, p1, p2, this.gameBoardPanel);

        /*MENUBAR*/
        super.setJMenuBar(createFileMenuBar());

        /*GAME BOARD PANEL*/
        super.add(this.gameBoardPanel);

        /*DATA PANEL*/
        super.add(this.dataPanel);

        super.setBackground(Color.WHITE);
        super.setLocation(GameUtils.GUI_FRAME_POSITION);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(GameUtils.GUI_FRAME_SIZE);
        super.setLayout(null);
        super.setResizable(false);
        super.setVisible(visible);
    }

    public DataPanel getDataPanel(){return this.dataPanel;}
    public GameBoardPanel getGameBoardPanel(){ return this.gameBoardPanel; }

    private JMenuBar createFileMenuBar(){
        JMenuBar fileMenuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("File");
        JMenuItem newGame = new JMenuItem("New game");
        JMenuItem save = new JMenuItem("Save game");
        JMenuItem load = new JMenuItem("Load game");
        JMenuItem exit = new JMenuItem("Exit");

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                p1.clearLostPieces();
                p2.clearLostPieces();
                board.restartGame();
                gameBoardPanel.refreshTiles();
                dataPanel.refreshDataPanel();
                System.out.println("Starting new game!");
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Saving game!");
                FileManager.saveGame(board, p1, p2);
                JOptionPane.showMessageDialog(null, "Game saved",  "Game saved", JOptionPane.WARNING_MESSAGE);
            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Loading game!");
                FileManager.loadGame(board, p1, p2, dataPanel, gameBoardPanel);
                JOptionPane.showMessageDialog(null, "Game loaded",  "Game loaded", JOptionPane.WARNING_MESSAGE);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Exit game!");
                System.exit(0);
            }
        });

        mainMenu.add(newGame);
        mainMenu.add(save);
        mainMenu.add(load);
        mainMenu.add(exit);
        fileMenuBar.add(mainMenu);

        return fileMenuBar;
    }
}

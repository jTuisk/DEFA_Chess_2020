package com.engine.ui;

import com.engine.FileManager;
import com.engine.GameUtils;
import com.engine.board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame {

    GameBoardPanel gameBoardPanel;
    DataPanel dataPanel;
    Board board;


    public UserInterface(Board board, boolean visible){
        super("DEFA Chess 2020, Made by Janek Tuisk");
        if(!visible)
            return;

        this.board = board;
        this.gameBoardPanel = new GameBoardPanel(board);
        this.dataPanel = new DataPanel(board, this.gameBoardPanel);

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
                board.restartGame();
                System.out.println("Starting new game!");
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Saving game!");
                FileManager.saveGame(board, board.getPlayer1(), board.getPlayer2());
                JOptionPane.showMessageDialog(null, "Game saved",  "Game saved", JOptionPane.WARNING_MESSAGE);
            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Loading game!");
                FileManager.loadGame(board);
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

package com.engine.ui;

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
    PawnPromotionPanel pawnPromotionPanel;

    public UserInterface(Board board, Player p1, Player p2){
        super("DEFA Chess 2020, Made by Janek Tuisk");
        this.dataPanel = new DataPanel(board, p1, p2);
        this.gameBoardPanel = new GameBoardPanel(board, dataPanel);
        this.pawnPromotionPanel = new PawnPromotionPanel(board);

        /*MENUBAR*/
        super.setJMenuBar(createFileMenuBar());

        /*PROMOTION*/
        super.add(this.pawnPromotionPanel);


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
        super.setVisible(true);
    }


    private JMenuBar createFileMenuBar(){
        JMenuBar fileMenuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("File");
        JMenuItem save = new JMenuItem("Save game");
        JMenuItem load = new JMenuItem("Load game");
        JMenuItem exit = new JMenuItem("Exit");

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Saving game!");
            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Loading game!");
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Exit game!");
                System.exit(0);
            }
        });

        mainMenu.add(save);
        mainMenu.add(load);
        mainMenu.add(exit);
        fileMenuBar.add(mainMenu);

        return fileMenuBar;
    }
}

package com.engine.ui;

import com.engine.GameUtils;
import com.engine.board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//https://docs.oracle.com/javase/tutorial/uiswing/layout/none.html

public class UserInterface extends JFrame {

    public UserInterface(Board board){
        super("DEFA Chess 2020, Made by Janek Tuisk");

        /*MENUBAR*/
        super.setJMenuBar(createFileMenuBar());

        /*GAME BOARD PANEL*/
        super.add(GameBoard.setupGameBoard(board));

        /*DATA PANEL*/
        super.add(GameBoard.setupDataPanel());


        super.setBackground(Color.WHITE);
        super.setLocation(GameUtils.GUI_FRAME_POSITION);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(GameUtils.GUI_FRAME_SIZE);
        super.setLayout(null);
        super.setResizable(false);
        super.setVisible(true);
        //super.pack();
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

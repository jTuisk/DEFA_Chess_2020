package com.engine.ui;

import com.engine.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame {

    public UserInterface(){
        super("DEFA Chess 2020, Made by Janek Tuisk");

        /*MENUBAR*/
        super.setJMenuBar(createFileMenuBar());

        super.setSize(GameUtils.GUI_FRAME_SIZE);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setVisible(true);
    }


    private JMenuBar createFileMenuBar(){
        JMenuBar fileMenuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("Save/Load");
        JMenuItem save = new JMenuItem("Save game");
        JMenuItem load = new JMenuItem("Load game");

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

        mainMenu.add(save);
        mainMenu.add(load);
        fileMenuBar.add(mainMenu);

        return fileMenuBar;
    }

}

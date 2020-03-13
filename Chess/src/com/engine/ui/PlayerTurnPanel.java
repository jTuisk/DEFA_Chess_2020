package com.engine.ui;

import com.engine.GameUtils;
import com.engine.board.Board;

import javax.swing.*;
import java.awt.*;

public class PlayerTurnPanel extends JPanel {

    private Board board;
    private int w_posX;
    private int w_posY;
    private int b_posX;
    private int b_posY;
    private int outerCircleRadius;
    private int radius;


    public PlayerTurnPanel(Board board, int w_posX, int w_posY , int b_posX, int b_posY, int radius, int outerCircleRadius){
        super();
        this.board = board;
        this.w_posX = w_posX;
        this.w_posY = w_posY;
        this.b_posX = b_posX;
        this.b_posY = b_posY;
        this.radius = radius;
        this.outerCircleRadius = outerCircleRadius;
        setupPanel();
    }

    private void setupPanel(){
        super.setBounds(GameUtils.DATA_FRAME_SIZE.width/10, GameUtils.DATA_FRAME_SIZE.width/10, 150, 80);
        super.setBackground(Color.green);
        JLabel text = new JLabel("PLAYER TURN");
        text.setBounds(35, 0, 100, 20);
        super.add(text);
        super.setLayout(null);
    }

    public void drawCircle(Graphics g, int x, int y, int r, Color color){
        Graphics2D g2d = (Graphics2D)g;
        x = x-(r/2);
        y = y-(r/2);
        g2d.setColor(color);
        g2d.fillOval(x, y, r, r);
        g2d.drawOval(x,y,r,r);
    }

    @Override
    public void paintComponent(Graphics g){
        if(board.getPlayerTurn().isWhite()){
            drawCircle(g, w_posX, w_posY, outerCircleRadius, GameUtils.SELECTED_TILE_COLOR);
        }else{
            drawCircle(g, b_posX, b_posY, outerCircleRadius, GameUtils.SELECTED_TILE_COLOR);
        }
        drawCircle(g, w_posX, w_posY, radius, Color.WHITE);
        drawCircle(g, b_posX, b_posY, radius, Color.BLACK);
    }

}
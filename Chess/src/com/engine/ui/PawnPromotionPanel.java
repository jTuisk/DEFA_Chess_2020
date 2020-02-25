package com.engine.ui;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.board.Board;
import com.engine.board.Tile;
import com.engine.piece.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class PawnPromotionPanel extends JPanel {

    Board board;
    GameBoardPanel gameBoardPanel;


    public PawnPromotionPanel(Board board, GameBoardPanel gameBoardPanel){
        super();
        this.board = board;
        this.gameBoardPanel = gameBoardPanel;
    }

    public void pawnPromotionPanelSetup(Piece piece){
        super.removeAll();
        super.setBounds(100,175, GameUtils.PAWN_PROMOTION_LABEL_SIZE.width,GameUtils.PAWN_PROMOTION_LABEL_SIZE.height);
        super.setBackground(GameUtils.BOARD_FRAME_COLOR);
        super.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, GameUtils.DARK_TILE_COLOR));
        JLabel header = new JLabel("<html><h1><u>Promote Pawn!</u></h1></html>");
        header.setBounds(100, 15, 200, 25);
        String[] imgNames = new String[]{"BISHOP", "KNIGHT", "QUEEN", "ROOK"};

        for(int i = 0; i < imgNames.length; i++){
            JPanel temp = new JPanel();
            String imgPath = "img/"+GameUtils.PLAYER_TURN+"_"+imgNames[i]+".png";
            temp.setBackground(GameUtils.BOARD_FRAME_COLOR);
            int x = 60+i*75;
            temp.setBounds(x, 70, 60, 60);
            String name = imgNames[i];
            temp.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    System.out.println(name);
                    Piece toPiece;
                    switch(name){
                        case "BISHOP":
                            toPiece = new Bishop(piece.getAlliance(), piece.getPlayer(), piece.getPosition());
                                break;
                        case "KNIGHT":
                            toPiece = new Knight(piece.getAlliance(), piece.getPlayer(), piece.getPosition());
                            break;
                        case "ROOK":
                            toPiece = new Rook(piece.getAlliance(), piece.getPlayer(), piece.getPosition());
                            break;
                        default: // case "QUEEN":
                            toPiece = new Queen(piece.getAlliance(), piece.getPlayer(), piece.getPosition());
                            break;
                    }
                    board.getTile(piece.getPosition()).setPiece(toPiece);
                    gameBoardPanel.refreshTiles(board);
                }

                @Override
                public void mousePressed(MouseEvent mouseEvent){}
                @Override
                public void mouseReleased(MouseEvent mouseEvent){}
                @Override
                public void mouseEntered(MouseEvent mouseEvent){}
                @Override
                public void mouseExited(MouseEvent mouseEvent){}
            });
            try {
                BufferedImage image = ImageIO.read(new File(imgPath));
                temp.add(new JLabel(new ImageIcon(image.getScaledInstance(60, 60, Image.SCALE_SMOOTH))));
                super.add(temp);
            } catch (Exception e) {
                System.out.println("IMG NOT FOUND!"+imgPath);
            }
        }
        super.add(header);
        super.setLayout(null);
    }
}

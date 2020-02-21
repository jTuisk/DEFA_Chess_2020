package com.engine.ui;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.board.Board;
import com.engine.piece.Piece;
import com.engine.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataPanel extends JPanel{

    private Board board;
    private Player p1;
    private Player p2;

    private ArrayList<JPanel> lostPiecesWhite;
    private ArrayList<JPanel> lostPiecesBlack;
    private JPanel lostPiecesWhitePanel;
    private JPanel lostPiecesBlackPanel;


    public DataPanel(Board board, Player p1, Player p2){
        super();
        this.p1 = p1;
        this.p2 = p2;
        this.lostPiecesWhite = setupLostPiecesList(p1);
        this.lostPiecesBlack = setupLostPiecesList(p2);
        this.lostPiecesWhitePanel = lostPiecesPanel(this.lostPiecesWhite);
        this.lostPiecesBlackPanel = lostPiecesPanel(this.lostPiecesBlack);
        this.board = board;
        setupDataPanel();
    }

    private void setupDataPanel(){
        super.setBounds(GameUtils.BOARD_FRAME_SIZE.width, 0, GameUtils.DATA_FRAME_SIZE.width, GameUtils.DATA_FRAME_SIZE.height);
        super.setBackground(GameUtils.BOARD_FRAME_COLOR);
        super.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, GameUtils.SELECTED_TILE_COLOR));

        super.setLayout(null);
        super.add(playerTurnPanel());
        super.add(playerPanel(90, this.p1, "PLAYER: WHITE"));
        super.add(playerPanel(310, this.p2, "PLAYER: BLACK"));
    }

    private JPanel playerTurnPanel(){
        JPanel playerTurnPanel = new JPanel();
        playerTurnPanel.setBounds(GameUtils.DATA_FRAME_SIZE.width/10, GameUtils.DATA_FRAME_SIZE.width/10, 150, 50);
        playerTurnPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, GameUtils.SELECTED_TILE_COLOR));
        playerTurnPanel.setBackground(GameUtils.BOARD_FRAME_COLOR);

        playerTurnPanel.setLayout(null);
        return playerTurnPanel;
    }

    private JPanel playerPanel(int posY, Player player, String headerText){
        JPanel playerPanel = new JPanel();
        playerPanel.setBounds(GameUtils.DATA_FRAME_SIZE.width/10, posY, 150, 200);
        playerPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, GameUtils.SELECTED_TILE_COLOR));
        playerPanel.setBackground(GameUtils.BOARD_FRAME_COLOR);
        JLabel header = new JLabel(headerText);
        header.setBounds(5, 10, (GameUtils.DATA_FRAME_SIZE.width/4*2), 10);


        //playerPanel.add(lostPiecesPanel(setupLostPiecesList(p1)));
        playerPanel.add(this.lostPiecesWhitePanel);
        //playerPanel.add(lostPiecesPanel(setupLostPiecesList(p2)));
        playerPanel.add(this.lostPiecesBlackPanel);
        playerPanel.add(header);
        playerPanel.setLayout(null);
        return playerPanel;
    }

    private JPanel lostPiecesPanel(ArrayList<JPanel> lostPieces){
        JPanel lostPiecesPanel = new JPanel();
        lostPiecesPanel.setBounds(10, 25, 130, 100);
        lostPiecesPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, GameUtils.SELECTED_TILE_COLOR));
        lostPiecesPanel.setBackground(GameUtils.BOARD_FRAME_COLOR);

        for(JPanel p : lostPieces){
            lostPieces.add(p);
        }

        lostPiecesPanel.setLayout(null);
        return lostPiecesPanel;
    }

    /*REWRITE*/
    private ArrayList<JPanel> setupLostPiecesList(Player player){
        ArrayList<JPanel> listOfPieces = new ArrayList<>();
        for(Piece piece : player.getLostPieces()) {
            if (piece != null) {
                JPanel temp = new JPanel();
                temp.setLayout(null);
                temp.setBounds(5, 5, 15, 15);
                String imgPath = "img/";
                imgPath += piece.getAlliance().isWhite() ? "WHITE_" : "BLACK_";
                try {
                    BufferedImage image = ImageIO.read(new File(imgPath + piece.getPieceType().getImgFileString()));
                    temp.add(new JLabel(new ImageIcon(image)));
                    listOfPieces.add(temp);
                } catch (Exception e) {
                    System.out.println("IMG NOT FOUND!");
                }
            }
        }
        return listOfPieces;
    }

    public void refreshDataPanelPieceList(){
        this.lostPiecesWhite = setupLostPiecesList(p1);
        this.lostPiecesBlack = setupLostPiecesList(p2);
        this.lostPiecesWhitePanel.removeAll();
        this.lostPiecesWhitePanel.revalidate();
        this.lostPiecesWhitePanel.repaint();
        this.lostPiecesBlackPanel.removeAll();
        this.lostPiecesBlackPanel.revalidate();
        this.lostPiecesBlackPanel.repaint();
        System.out.println("Refreshing dataPanel piece list!");
    }
}

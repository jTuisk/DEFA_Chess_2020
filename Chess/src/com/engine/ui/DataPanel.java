package com.engine.ui;

import com.engine.GameUtils;
import com.engine.board.Board;
import com.engine.piece.Piece;
import com.engine.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class DataPanel extends JPanel{

    private Board board;
    private Player p1;
    private Player p2;


    public DataPanel(Board board, Player p1, Player p2){
        super();
        this.p1 = p1;
        this.p2 = p2;
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


        playerPanel.add(lostPiecesPanel(setupLostPiecesList(player)));
        playerPanel.add(lostPiecesPanel(setupLostPiecesList(player)));
        playerPanel.add(header);
        playerPanel.setLayout(null);
        return playerPanel;
    }

    private JPanel lostPiecesPanel(ArrayList<JPanel> lostPieces){
        JPanel lostPiecesPanel = new JPanel();
        lostPiecesPanel.setBounds(10, 25, 130, 100);
        lostPiecesPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, GameUtils.SELECTED_TILE_COLOR));
        lostPiecesPanel.setBackground(GameUtils.BOARD_FRAME_COLOR);

        for(int i = 0; i < lostPieces.size(); i++){
            int columnsInRow = 5;
            int iconPosX = 8+((i % columnsInRow) *20);
            int iconPosY = 8+(20*(i/5));
            lostPieces.get(i).setBounds(iconPosX, iconPosY, 25, 25);
            lostPiecesPanel.add(lostPieces.get(i));
        }

        lostPiecesPanel.setLayout(null);
        return lostPiecesPanel;
    }

    private ArrayList<JPanel> setupLostPiecesList(Player player){
        ArrayList<JPanel> listOfPieces = new ArrayList<>();
        System.out.println("Player("+player.getAlliance()+": "+player.getLostPieces().toString());
        for(Piece piece : player.getLostPieces()) {
            if (piece != null && piece.getAlliance() == player.getAlliance()) {
                JPanel temp = new JPanel();
                String imgPath = "img/";
                temp.setBackground(GameUtils.BOARD_FRAME_COLOR);
                imgPath += piece.getAlliance()+"_";
                try {
                    BufferedImage image = ImageIO.read(new File(imgPath + piece.getPieceType().getImgFileString()));
                    temp.add(new JLabel(new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH))));
                    listOfPieces.add(temp);
                } catch (Exception e) {
                    System.out.println("IMG NOT FOUND!");
                }
            }
        }
        return listOfPieces;
    }



    public void refreshDataPanel(){
        super.removeAll();
        setupDataPanel();
        System.out.println("Refreshing dataPanel piece list!");
    }
}

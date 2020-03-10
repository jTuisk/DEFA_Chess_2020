package com.engine.ui;

import com.engine.Game;
import com.engine.GameStatus;
import com.engine.GameUtils;
import com.engine.board.Board;
import com.engine.piece.*;
import com.engine.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class DataPanel extends JPanel{

    private GameBoardPanel gameBoardPanel;
    private Board board;
    private Player p1;
    private Player p2;


    public DataPanel(Board board, Player p1, Player p2, GameBoardPanel gameBoardPanel){
        super();
        this.gameBoardPanel = gameBoardPanel;
        this.p1 = p1;
        this.p2 = p2;
        this.board = board;
        setupDataPanel();
    }

    public void refreshDataPanel(){
        setupDataPanel();
    }

    private void setupDataPanel(){
        super.removeAll();
        switch(this.board.getGameStatus()){
            case PROMOTING_PAWN:
                pawnPromotionPanelSetup();
                    break;
            default:
                dataPanelSetup();
                    break;
        }
    }

    private void dataPanelSetup(){
        super.removeAll();
        if(this.board.getGameStatus() == GameStatus.PROMOTING_PAWN)
            setupDataPanel();

        super.setBounds(GameUtils.BOARD_FRAME_SIZE.width, 0, GameUtils.DATA_FRAME_SIZE.width, GameUtils.DATA_FRAME_SIZE.height);
        super.setBackground(GameUtils.BOARD_FRAME_COLOR);
        super.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, GameUtils.SELECTED_TILE_COLOR));

        super.setLayout(null);
        super.add(playerTurnPanel());
        super.add(playerPanel(90, this.p1, "PLAYER: WHITE"));
        super.add(playerPanel(310, this.p2, "PLAYER: BLACK"));
    }

    private void pawnPromotionPanelSetup(){
        if(this.board.getGameStatus() != GameStatus.PROMOTING_PAWN)
            setupDataPanel();

        super.setBounds(GameUtils.BOARD_FRAME_SIZE.width, 0, GameUtils.DATA_FRAME_SIZE.width, GameUtils.DATA_FRAME_SIZE.height);
        super.setBackground(GameUtils.BOARD_FRAME_COLOR);
        super.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, GameUtils.SELECTED_TILE_COLOR));

        super.setLayout(null);


        JLabel header = new JLabel("<html><h1><u>PromotePawn!</u></h1></html>");
        header.setBounds(10, 20, 200, 25);
        String[] imgNames = new String[]{"BISHOP", "KNIGHT", "QUEEN", "ROOK"};

        for(int i = 0; i < imgNames.length; i++){
            JPanel temp = new JPanel();
            String imgPath = "img/"+this.board.getLastMovedPiece().getAlliance()+"_"+imgNames[i]+".png";
            temp.setBackground(GameUtils.BOARD_FRAME_COLOR);
            int y = 60+i*75;
            temp.setBounds(60, y, 60, 60);
            String name = imgNames[i];
            temp.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    Piece toPiece;
                    Piece piece = board.getLastMovedPiece();
                    switch(name){
                        case "BISHOP":
                            piece.getPlayer().removePieceFromPlayer(piece);
                            toPiece = new Bishop(board, piece.getAlliance(), piece.getPlayer(), piece.getPosition());
                            toPiece.getPlayer().addPieceToPlayer(toPiece);
                            piece.getPlayer().kingUnderAttack();
                            board.checkChessWinCondition(piece.getPlayer().getEnemyPlayer());
                            break;
                        case "KNIGHT":
                            piece.getPlayer().removePieceFromPlayer(piece);
                            toPiece = new Knight(board, piece.getAlliance(), piece.getPlayer(), piece.getPosition());
                            toPiece.getPlayer().addPieceToPlayer(toPiece);
                            piece.getPlayer().kingUnderAttack();
                            board.checkChessWinCondition(piece.getPlayer().getEnemyPlayer());
                            break;
                        case "ROOK":
                            toPiece = new Rook(board, piece.getAlliance(), piece.getPlayer(), piece.getPosition());
                            board.getFutureBoard().getTile(piece.getPosition()).setPiece(new Rook(board, piece.getAlliance(), piece.getPlayer(), piece.getPosition()));
                            piece.getPlayer().kingUnderAttack();
                            board.checkChessWinCondition(piece.getPlayer().getEnemyPlayer());
                            break;
                        default: // case "QUEEN":
                            //piece.getPlayer().promotePawn(piece, new Queen(board, piece.getAlliance(), piece.getPlayer(), piece.getPosition()),
                                                               // new Queen(board, piece.getAlliance(), piece.getPlayer(), piece.getPosition()));
                            //piece.getPlayer().removePieceFromPlayer(piece);
                            toPiece = new Queen(board, piece.getAlliance(), piece.getPlayer(), piece.getPosition());
                            Piece f_toPiece = new Queen(board, piece.getAlliance(), piece.getPlayer(), piece.getPosition());
                            int i = piece.getPlayer().getPlayerPieces().indexOf(piece);
                            int j = board.getFutureBoard().getTile(piece.getPosition()).getPiece().getPlayer().getPlayerPieces().indexOf(board.getFutureBoard().getTile(piece.getPosition()).getPiece());
                            piece.getPlayer().getPlayerPieces().set(i, toPiece);
                            //board.getFutureBoard().getTile(piece.getPosition()).getPiece().getPlayer().getPlayerPieces().set(j, f_toPiece);
                            System.out.println();
                            toPiece.getPlayer().addPieceToPlayer(toPiece);
                            piece.getPlayer().kingUnderAttack();
                            board.checkChessWinCondition(piece.getPlayer().getEnemyPlayer());
                            break;
                    }
                    board.getTile(piece.getPosition()).setPiece(toPiece);
                    board.setGameStatus(GameStatus.PLAYER_TURN);
                    dataPanelSetup();
                    if(gameBoardPanel != null)
                        gameBoardPanel.refreshTiles();
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

    private JPanel playerTurnPanel(){
        JPanel playerTurnPanel = new JPanel();
        playerTurnPanel.setBounds(GameUtils.DATA_FRAME_SIZE.width/10, GameUtils.DATA_FRAME_SIZE.width/10, 150, 50);
        playerTurnPanel.setBackground(GameUtils.BOARD_FRAME_COLOR);

        JLabel text = new JLabel("PLAYER TURN");
        text.setBounds(35, 0, 100, 20);

        JPanel colorBox = new JPanel();
        colorBox.setBounds(45, 25, 60, 40);
        colorBox.setBackground(this.board.getPlayerTurn().isWhite()? Color.WHITE : Color.BLACK);

        playerTurnPanel.add(text);
        playerTurnPanel.add(colorBox);
        playerTurnPanel.setLayout(null);
        return playerTurnPanel;
    }

    private JPanel playerPanel(int posY, Player player, String headerText){
        JPanel playerPanel = new JPanel();
        playerPanel.setBounds(GameUtils.DATA_FRAME_SIZE.width/10, posY, 150, 200);
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
        lostPiecesPanel.setBackground(GameUtils.BOARD_FRAME_COLOR);

        JLabel text = new JLabel("Lost pieces:");
        text.setBounds(8, 0, 100, 20);
        lostPiecesPanel.add(text);

        for(int i = 0; i < lostPieces.size(); i++){
            int columnsInRow = 5;
            int iconPosX = 10+((i % columnsInRow) *20);
            int iconPosY = 10+(20*(i/5));
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
}

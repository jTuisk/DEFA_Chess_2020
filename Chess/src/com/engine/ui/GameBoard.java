package com.engine.ui;

import com.engine.Game;
import com.engine.GameUtils;
import com.engine.board.Board;
import com.engine.board.Tile;
import com.engine.piece.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;


public class GameBoard {

    JPanel[][] tiles;

    public GameBoard(){
        tiles = new JPanel[GameUtils.GAME_BOARD_SIZE_WIDTH][GameUtils.GAME_BOARD_SIZE_HEIGHT];
    }

    public static JPanel setupDataPanel(){
        JPanel dataPanel = new JPanel();
        dataPanel.setBounds(GameUtils.BOARD_FRAME_SIZE.width, 0, GameUtils.DATA_FRAME_SIZE.width, GameUtils.DATA_FRAME_SIZE.height);
        dataPanel.setBackground(Color.WHITE);
        return dataPanel;
    }

    public JPanel setupGameBoard(Board board) {
        JPanel gameBoard = new JPanel();
        gameBoard.setBackground(GameUtils.BOARD_FRAME_COLOR);
        gameBoard.setBounds(0,0, GameUtils.BOARD_FRAME_SIZE.width, GameUtils.BOARD_FRAME_SIZE.height);
        gameBoard.setLayout(null);

        setupBoardCoordinateLabels(gameBoard);

        for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
            for(int y = 0; y < GameUtils.GAME_BOARD_SIZE_WIDTH; y++){
                this.tiles[x][y] = createNewTile(gameBoard, board, new int[]{x,y});
            }
        }

        return gameBoard;
    }

    private static void setupBoardCoordinateLabels(JPanel gameBoard){
        for(int i = 0; i < GameUtils.GAME_BOARD_VERTICAL_TILE_COORDINATES.length ; i++){
            JLabel temp = new JLabel(GameUtils.GAME_BOARD_VERTICAL_TILE_COORDINATES[i]);
            int xPos = (GameUtils.SINGLE_TILE_SIZE.width*i) + (GameUtils.SINGLE_TILE_SIZE.width);
            int yPos = (GameUtils.SINGLE_TILE_SIZE.height/4)*-1;
            temp.setBounds(xPos, yPos, GameUtils.SINGLE_TILE_SIZE.width, GameUtils.SINGLE_TILE_SIZE.height);
            temp.setForeground(Color.BLACK);
            gameBoard.add(temp);
        }

        for(int i = 0; i < GameUtils.GAME_BOARD_HORIZONTAL_TILE_COORDINATES.length; i++){
            JLabel temp = new JLabel(GameUtils.GAME_BOARD_HORIZONTAL_TILE_COORDINATES[i]);
            int xPos = (GameUtils.SINGLE_TILE_SIZE.width/4);
            int yPos = (GameUtils.SINGLE_TILE_SIZE.height*i) + (GameUtils.SINGLE_TILE_SIZE.height)/2;
            temp.setBounds(xPos, yPos, GameUtils.SINGLE_TILE_SIZE.width, GameUtils.SINGLE_TILE_SIZE.height);
            temp.setForeground(Color.BLACK);
            gameBoard.add(temp);
        }
    }

    public JPanel createNewTile(JPanel gameBoard, Board board, int[] pos){
        JPanel tile = new JPanel();
        int xPos = (GameUtils.SINGLE_TILE_SIZE.width*pos[0]) + (GameUtils.SINGLE_TILE_SIZE.width/2);
        int yPos = (GameUtils.SINGLE_TILE_SIZE.height*pos[1]) + (GameUtils.SINGLE_TILE_SIZE.height/2);
        tile.setBounds(xPos, yPos, GameUtils.SINGLE_TILE_SIZE.width, GameUtils.SINGLE_TILE_SIZE.height);
        tile.setBackground(getTileBackgroundColor(pos));
        assignTilePieceIcon(tile, board, pos);
        tile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(SwingUtilities.isLeftMouseButton(mouseEvent)){
                    Game.selectedPiece = board.getTile(pos).getPiece();
                    if(Game.selectedPiece != null){
                        System.out.println(Game.selectedPiece+": "+Game.selectedPiece.getAlliance()+" : "+Arrays.toString(Game.selectedPiece.getPosition())+" -> "+Game.selectedPiece.getAllAvailableMoves(board));
                        assignTileShadow(tile, board);
                    }
                        /*if(Game.selectedPiece == null){
                            Game.selectedPiece = board.getTile(pos).getPiece();
                            //System.out.println(Game.selectedPiece);
                        }else{
                            if(Piece.canMove(Game.selectedPiece.getAllAvailableMoves(board), new Piece.Move(board, Game.selectedPiece, pos))){
                                Game.selectedPiece.setPiecePosition(pos);
                                board.getTile(pos).setPiece(Game.selectedPiece);
                                gameBoard.removeAll();
                                gameBoard.revalidate();
                                gameBoard.repaint();
                                System.out.println("Move done!");
                            }
                        }*/
                    }else{
                        Game.selectedPiece = null;
                    }
                }

                @Override
                public void mousePressed(MouseEvent mouseEvent) { }
                @Override
                public void mouseReleased(MouseEvent mouseEvent) {  }
                @Override
                public void mouseEntered(MouseEvent mouseEvent) {  }
                @Override
                public void mouseExited(MouseEvent mouseEvent) { }
            });
            gameBoard.add(tile);
        return tile;
    }

    private void assignTilePieceIcon(JPanel tile, Board board, int[] pos){
        tile.removeAll();
        Piece piece = board.getTile(pos).getPiece();
        if(piece != null){
            String imgPath = "img/";
            imgPath += piece.getAlliance().isWhite() ? "WHITE_" : "BLACK_";
            try {
                BufferedImage image = ImageIO.read(new File(imgPath + piece.getPieceType().getImgFileString()));
                tile.add(new JLabel(new ImageIcon(image)));
            }catch (Exception e){
                System.out.println("IMG NOT FOUND!");
            }
        }
    }

    private void resetTileShadows(){
        for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
            for(int y = 0; y < GameUtils.GAME_BOARD_SIZE_WIDTH; y++){
                this.tiles[x][y].setBackground(getTileBackgroundColor(new int[] {x,y}));
            }
        }
    }

    private void assignTileShadow(JPanel tile, Board board){
        tile.removeAll();
        resetTileShadows();
        Piece piece = Game.selectedPiece;
        if(piece != null){
            for(Piece.Move move : Game.selectedPiece.getAllAvailableMoves(board)){
                this.tiles[move.getDestCoords()[0]][move.getDestCoords()[1]].setBackground(Color.GREEN);
            }
        }
    }

    private Color getTileBackgroundColor(int[] pos){
        if((pos[0] + pos[1]) % 2 == 0)
            return GameUtils.DARK_TILE_COLOR;
        return GameUtils.LIGHT_TILE_COLOR;
    }
}
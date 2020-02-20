package com.engine.ui;

import com.engine.GameUtils;
import com.engine.board.Board;
import com.engine.piece.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;


public class GameBoard {

    static JPanel[][] tiles;

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
                    if(GameUtils.SELECTED_PIECE == null){
                        selectPiece(tile, board, pos);
                    }else{
                        if(Piece.canMove(GameUtils.SELECTED_PIECE.getAllAvailableMoves(board), new Piece.Move(board, GameUtils.SELECTED_PIECE, pos))){
                            GameUtils.SELECTED_PIECE.finishMove(board, pos);
                            deselectPiece(tile, board);
                        }else{
                            if(board.getTile(pos).isEmpty()){
                                deselectPiece(tile, board);
                            }else{
                                selectPiece(tile, board, pos);
                            }
                        }
                    }
                    }else{
                        deselectPiece(tile, board);
                    }
                    refreshTiles(board);
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
            gameBoard.add(tile);
        return tile;
    }

    private void deselectPiece(JPanel tile, Board board){
        GameUtils.SELECTED_PIECE = null;
        resetTileShadows(board);
    }

    private void selectPiece(JPanel tile, Board board, int[] pos){
        GameUtils.selectPiece(board.getTile(pos).getPiece());
        assignTileShadow(tile, board);
    }

    public static void refreshTiles(Board board){
        resetTileShadows(board);
        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles[x].length; y++){
                assignTilePieceIcon(tiles[x][y], board, new int[] {x,y});
                tiles[x][y].revalidate();
                tiles[x][y].repaint();
            }
        }
    }

    private static void assignTilePieceIcon(JPanel tile, Board board, int[] pos){
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
        }else
            tile.removeAll();
    }

    private static void resetTileShadows(Board board){
        for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
            for(int y = 0; y < GameUtils.GAME_BOARD_SIZE_WIDTH; y++){
                GameBoard.tiles[x][y].setBorder(null);
            }
        }
        if(GameUtils.SELECTED_PIECE != null)
            assignTileShadow(GameBoard.tiles[GameUtils.SELECTED_PIECE.getPosition()[0]][GameUtils.SELECTED_PIECE.getPosition()[1]], board);
    }

    private static void assignTileShadow(JPanel tile, Board board){
        Piece piece = GameUtils.SELECTED_PIECE;
        if(piece != null){
            GameBoard.tiles[piece.getPosition()[0]][piece.getPosition()[1]].setBorder(BorderFactory.createMatteBorder(3,3,3,3,GameUtils.SELECTED_TILE_COLOR));
            for(Piece.Move move : GameUtils.SELECTED_PIECE.getAllAvailableMoves(board)){
                GameBoard.tiles[move.getDestCoords()[0]][move.getDestCoords()[1]].setBorder(BorderFactory.createMatteBorder(3,3,3,3,GameUtils.TILE_BORDER_COLOR));
            }
        }
    }

    private Color getTileBackgroundColor(int[] pos){
        if((pos[0] + pos[1]) % 2 == 0)
            return GameUtils.DARK_TILE_COLOR;
        return GameUtils.LIGHT_TILE_COLOR;
    }
}
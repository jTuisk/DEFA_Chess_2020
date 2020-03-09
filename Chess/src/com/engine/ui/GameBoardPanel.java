package com.engine.ui;

import com.engine.GameStatus;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;
import com.engine.piece.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;


public class GameBoardPanel extends  JPanel{

    private JPanel[][] tiles;
    private Board board;


    public GameBoardPanel(Board board){
        super();
        this.tiles = new JPanel[GameUtils.GAME_BOARD_SIZE_WIDTH][GameUtils.GAME_BOARD_SIZE_HEIGHT];
        this.board = board;
        super.setBackground(GameUtils.BOARD_FRAME_COLOR);
        super.setBounds(0,0, GameUtils.BOARD_FRAME_SIZE.width, GameUtils.BOARD_FRAME_SIZE.height);
        super.setLayout(null);
        setupBoardCoordinateLabels();

        for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
            for(int y = 0; y < GameUtils.GAME_BOARD_SIZE_WIDTH; y++){
                this.tiles[x][y] = createNewTile(new int[]{x,y});
            }
        }
    }

    private void setupBoardCoordinateLabels(){
        for(int i = 0; i < GameUtils.GAME_BOARD_VERTICAL_TILE_COORDINATES.length ; i++){
            JLabel temp = new JLabel(GameUtils.GAME_BOARD_VERTICAL_TILE_COORDINATES[i]);
            int xPos = (GameUtils.SINGLE_TILE_SIZE.width*i) + (GameUtils.SINGLE_TILE_SIZE.width);
            int yPos = (GameUtils.SINGLE_TILE_SIZE.height/4)*-1;
            temp.setBounds(xPos, yPos, GameUtils.SINGLE_TILE_SIZE.width, GameUtils.SINGLE_TILE_SIZE.height);
            temp.setForeground(Color.BLACK);
            super.add(temp);
        }

        for(int i = 0; i < GameUtils.GAME_BOARD_HORIZONTAL_TILE_COORDINATES.length; i++){
            JLabel temp = new JLabel(GameUtils.GAME_BOARD_HORIZONTAL_TILE_COORDINATES[i]);
            int xPos = (GameUtils.SINGLE_TILE_SIZE.width/4);
            int yPos = (GameUtils.SINGLE_TILE_SIZE.height*i) + (GameUtils.SINGLE_TILE_SIZE.height)/2;
            temp.setBounds(xPos, yPos, GameUtils.SINGLE_TILE_SIZE.width, GameUtils.SINGLE_TILE_SIZE.height);
            temp.setForeground(Color.BLACK);
            super.add(temp);
        }
    }

    private JPanel createNewTile( int[] pos){
        JPanel tile = new JPanel();
        int xPos = (GameUtils.SINGLE_TILE_SIZE.width*pos[0]) + (GameUtils.SINGLE_TILE_SIZE.width/2);
        int yPos = (GameUtils.SINGLE_TILE_SIZE.height*pos[1]) + (GameUtils.SINGLE_TILE_SIZE.height/2);
        tile.setBounds(xPos, yPos, GameUtils.SINGLE_TILE_SIZE.width, GameUtils.SINGLE_TILE_SIZE.height);
        tile.setBackground(getTileBackgroundColor(pos));
        assignTilePieceIcon(tile, pos);
        tile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(board.getGameStatus() != GameStatus.GAME_OVER) {
                    if (board.getGameStatus() == GameStatus.PLAYER_TURN) {
                        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
                            if (board.getSelectedPiece() == null) {
                                selectPiece(tile, pos);
                            } else {
                                if (Piece.canMove(board.getSelectedPiece().getAllAvailableMoves(), new Piece.Move(board, board.getSelectedPiece(), pos))) {
                                    board.getSelectedPiece().finishMove(pos);
                                    refreshTiles();
                                } else {
                                    if (board.getTile(pos).isEmpty()) {
                                        deselectPiece();
                                    } else {
                                        selectPiece(tile, pos);
                                    }
                                }
                            }
                        } else {
                            deselectPiece();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "You have to promote the pawn before doing anything else.", "ErrorMsg", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Game is finished. Please do start new game.",  "ErrorMsg", JOptionPane.WARNING_MESSAGE);
                }
                refreshTiles();
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
        super.add(tile);
        return tile;
    }

    private void deselectPiece(){
        this.board.setSelectPiece(null);
        resetTileShadows();
    }

    private void selectPiece(JPanel tile, int[] pos){
        this.board.setSelectPiece(board.getTile(pos).getPiece());
        assignTileShadow();
    }

    public void refreshTiles(){
        resetTileShadows();
        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles[x].length; y++){
                assignTilePieceIcon(tiles[x][y], new int[] {x,y});
                tiles[x][y].revalidate();
                tiles[x][y].repaint();
            }
        }
    }

    private void assignTilePieceIcon(JPanel tile, int[] pos){
        tile.removeAll();
        Piece piece = this.board.getTile(pos).getPiece();
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

    private void resetTileShadows(){
        for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
            for(int y = 0; y < GameUtils.GAME_BOARD_SIZE_WIDTH; y++){
                this.tiles[x][y].setBorder(null);
            }
        }
        if(this.board.getSelectedPiece() != null)
            assignTileShadow();
    }

    private void assignTileShadow(){
        Piece piece = this.board.getSelectedPiece();
        if(piece != null){
            this.tiles[piece.getPosition()[0]][piece.getPosition()[1]].setBorder(BorderFactory.createMatteBorder(3,3,3,3,GameUtils.SELECTED_TILE_COLOR));
            for(Piece.Move move : this.board.getSelectedPiece().getAllAvailableMoves()){
                int x = move.getDestCoords()[0];
                int y = move.getDestCoords()[1];
                Color assignColor = GameUtils.MOVABLE_TILE_BORDER_COLOR;
                Piece pieceAtDestination = board.getTile(move.getDestCoords()).getPiece();
                if(pieceAtDestination != null){
                    if(pieceAtDestination.getAlliance() != this.board.getPlayerTurn())
                        assignColor = GameUtils.TILE_UNDER_ATTACK_COLOR;
                    if(pieceAtDestination.getPieceType() == PieceType.KING)
                        assignColor = GameUtils.KING_UNDER_ATTACK_COLOR;
                }
                this.tiles[x][y].setBorder(BorderFactory.createMatteBorder(3,3,3,3,assignColor));
            }
        }
    }

    private Color getTileBackgroundColor(int[] pos){
        if((pos[0] + pos[1]) % 2 == 0)
            return GameUtils.DARK_TILE_COLOR;
        return GameUtils.LIGHT_TILE_COLOR;
    }
}
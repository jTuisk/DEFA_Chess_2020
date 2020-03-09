package com.engine.board;

import com.engine.Alliance;
import com.engine.GameStatus;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.piece.*;
import com.engine.player.Player;
import com.engine.ui.UserInterface;

import javax.swing.*;
import java.util.ArrayList;

public class Board {

    private Tile[][] currentGameBoard;
    private Player p1;
    private Player p2;
    private GameStatus gameStatus = null;
    private Piece lastMovedPiece = null;
    private Alliance playerTurn = null;
    private Piece selectedPiece  = null;
    private UserInterface userInterface;
    private boolean userInterfaceVisible;
    private ArrayList<Piece> piecesOnBoard = new ArrayList<>();

    public Board(boolean UI){
        this.gameStatus = GameStatus.INITIALIZING_GAME;
        this.p1 = new Player(Alliance.WHITE);
        this.p2 = new Player(Alliance.BLACK);
        this.currentGameBoard = defaultBoardSetup();
        this.playerTurn = Alliance.WHITE;
        this.gameStatus = GameStatus.PLAYER_TURN;
        this.userInterface = new UserInterface(this, p1, p2, UI);
        this.userInterfaceVisible = UI;
    }

    public void restartGame(){
        this.gameStatus = GameStatus.INITIALIZING_GAME;
        this.currentGameBoard = defaultBoardSetup();
        this.p1 = new Player(Alliance.WHITE);
        this.p2 = new Player(Alliance.BLACK);
        this.playerTurn = Alliance.WHITE;
        this.gameStatus = GameStatus.PLAYER_TURN;
        this.userInterface = new UserInterface(this, p1, p2, userInterfaceVisible);
    }

    public Player getPlayer1(){return this.p1;}
    public Player getPlayer2(){return this.p2;}

    public UserInterface getUserInterface(){return this.userInterface;}

    public GameStatus getGameStatus(){return this.gameStatus;}
    public void setGameStatus(GameStatus gameStatus){ this.gameStatus = gameStatus;}

    public Piece getLastMovedPiece(){return this.lastMovedPiece;}
    public void setLastMovedPiece(Piece piece){this.lastMovedPiece = piece;}

    public Alliance getPlayerTurn(){return this.playerTurn;}
    public void setPlayerTurn(Alliance playerTurn){this.playerTurn = playerTurn;}
    public void changePlayerTurn(){
        playerTurn = playerTurn.isWhite() ? Alliance.BLACK : Alliance.WHITE;
    }

    public Piece getSelectedPiece(){return this.selectedPiece;}
    public void setSelectPiece(Piece piece){
        if(piece == null || piece.getAlliance() == playerTurn)
            this.selectedPiece = piece;
    }
    public ArrayList<Piece> getPiecesOnBoard(){return this.piecesOnBoard;}

    public void refreshUI(){
        this.userInterface.getDataPanel().refreshDataPanel();
        this.userInterface.getGameBoardPanel().refreshTiles();
    }

    public void removePieceFromList(Player player, int[] destPos){
        player.addLostPiece(getTile(destPos).getPiece());
        this.piecesOnBoard.remove(getTile(destPos).getPiece());
    }

    public int kingUnderAttack(){
        for(Piece.Move move : getAllEnemyMoves(playerTurn)){
            if(getTile(move.getDestCoords()).getPiece() == null)
                continue;

            Piece piece = getTile(move.getDestCoords()).getPiece();

            if(piece.getPieceType() == PieceType.KING && piece.getAlliance() == playerTurn){
                if(getTile(move.getDestCoords()).getPiece().getAllAvailableMoves().size() < 1){
                    JOptionPane.showMessageDialog(null, "Checkmate! Player "+this.playerTurn.getEnemy()+" won!", "Checkmate", JOptionPane.WARNING_MESSAGE);
                    this.gameStatus = GameStatus.GAME_OVER;
                    return 2;
                } else{
                    JOptionPane.showMessageDialog(null, "Check! Player "+this.playerTurn+" king is under attack!", "Check", JOptionPane.WARNING_MESSAGE);
                    return 1;
                }
            }
        }
        return 0;
    }

    public ArrayList<Piece.Move> getAllEnemyMoves(Alliance alliance){
        ArrayList<Piece.Move> moves = new ArrayList<>();

        for(Piece piece : this.piecesOnBoard){
            if(piece.getAlliance() == alliance ||piece.getPieceType() == PieceType.KING)
                continue;
            moves.addAll(piece.getAllAvailableMoves());
        }

        return moves;
    }


    private Tile[][] defaultBoardSetup(){
        Tile[][] setup = new Tile[GameUtils.GAME_BOARD_SIZE_HEIGHT][GameUtils.GAME_BOARD_SIZE_WIDTH];
        for(int x = 0; x < setup.length; x++){
            for(int y = 0; y < setup.length; y++){
                setup[x][y] = new Tile(new int[]{x, y});
            }
        }
        /**
         * WHITE
         */
        setup[0][0] = new Tile(new int[]{0,0}, new Rook(this, Alliance.WHITE, this.p1, new int[]{0,0}));
        setup[0][1] = new Tile(new int[]{0,1}, new Knight(this, Alliance.WHITE, this.p1, new int[]{0,1}));
        setup[0][2] = new Tile(new int[]{0,2}, new Bishop(this, Alliance.WHITE, this.p1, new int[]{0,2}));
        setup[0][3] = new Tile(new int[]{0,3}, new Queen(this, Alliance.WHITE, this.p1, new int[]{0,3}));
        setup[0][4] = new Tile(new int[]{0,4}, new King(this, Alliance.WHITE, this.p1, new int[]{0,4}));
        setup[0][5] = new Tile(new int[]{0,5}, new Bishop(this, Alliance.WHITE, this.p1, new int[]{0,5}));
        setup[0][6] = new Tile(new int[]{0,6}, new Knight(this, Alliance.WHITE, this.p1, new int[]{0,6}));
        setup[0][7] = new Tile(new int[]{0,7}, new Rook(this, Alliance.WHITE, this.p1, new int[]{0,7}));
        setup[1][0] = new Tile(new int[]{1,0}, new Pawn(this, Alliance.WHITE, this.p1, new int[]{1,0}));
        setup[1][1] = new Tile(new int[]{1,1}, new Pawn(this, Alliance.WHITE, this.p1, new int[]{1,1}));
        setup[1][2] = new Tile(new int[]{1,2}, new Pawn(this, Alliance.WHITE, this.p1, new int[]{1,2}));
        setup[1][3] = new Tile(new int[]{1,3}, new Pawn(this, Alliance.WHITE, this.p1, new int[]{1,3}));
        setup[1][4] = new Tile(new int[]{1,4}, new Pawn(this, Alliance.WHITE, this.p1, new int[]{1,4}));
        setup[1][5] = new Tile(new int[]{1,5}, new Pawn(this, Alliance.WHITE, this.p1, new int[]{1,5}));
        setup[1][6] = new Tile(new int[]{1,6}, new Pawn(this, Alliance.WHITE, this.p1, new int[]{1,6}));
        setup[1][7] = new Tile(new int[]{1,7}, new Pawn(this, Alliance.WHITE, this.p1, new int[]{1,7}));


        /**
         * BLACK
         */
        setup[6][0] = new Tile(new int[]{6,0}, new Pawn(this, Alliance.BLACK, this.p2, new int[]{6,0}));
        setup[6][1] = new Tile(new int[]{6,1}, new Pawn(this, Alliance.BLACK, this.p2, new int[]{6,1}));
        setup[6][2] = new Tile(new int[]{6,2}, new Pawn(this, Alliance.BLACK, this.p2, new int[]{6,2}));
        setup[6][3] = new Tile(new int[]{6,3}, new Pawn(this, Alliance.BLACK, this.p2, new int[]{6,3}));
        setup[6][4] = new Tile(new int[]{6,4}, new Pawn(this, Alliance.BLACK, this.p2, new int[]{6,4}));
        setup[6][5] = new Tile(new int[]{6,5}, new Pawn(this, Alliance.BLACK, this.p2, new int[]{6,5}));
        setup[6][6] = new Tile(new int[]{6,6}, new Pawn(this, Alliance.BLACK, this.p2, new int[]{6,6}));
        setup[6][7] = new Tile(new int[]{6,7}, new Pawn(this, Alliance.BLACK, this.p2, new int[]{6,7}));
        setup[7][0] = new Tile(new int[]{7,0}, new Rook(this, Alliance.BLACK, this.p2, new int[]{7,0}));
        setup[7][1] = new Tile(new int[]{7,1}, new Knight(this, Alliance.BLACK, this.p2, new int[]{7,1}));
        setup[7][2] = new Tile(new int[]{7,2}, new Bishop(this, Alliance.BLACK, this.p2, new int[]{7,2}));
        setup[7][3] = new Tile(new int[]{7,3}, new Queen(this, Alliance.BLACK, this.p2, new int[]{7,3}));
        setup[7][4] = new Tile(new int[]{7,4}, new King(this, Alliance.BLACK, this.p2, new int[]{7,4}));
        setup[7][5] = new Tile(new int[]{7,5}, new Bishop(this, Alliance.BLACK, this.p2, new int[]{7,5}));
        setup[7][6] = new Tile(new int[]{7,6}, new Knight(this, Alliance.BLACK, this.p2, new int[]{7,6}));
        setup[7][7] = new Tile(new int[]{7,7}, new Rook(this, Alliance.BLACK, this.p2, new int[]{7,7}));


        return setup;
    }

    public boolean posInGameBoard(int[] pos){
        if(pos == null || pos.length != 2)
            return false;
        return (pos[0] >= 0 && pos[0] < GameUtils.GAME_BOARD_SIZE_HEIGHT && pos[1] >= 0 && pos[1] < GameUtils.GAME_BOARD_SIZE_WIDTH);
    }


    public Tile getTile(int[] pos){
        return currentGameBoard[pos[0]][pos[1]];
    }

    public Player getP1(){return this.p1;}
    public Player getP2(){return this.p2;}

    @Override
    public String toString(){
        final StringBuilder builder = new StringBuilder();
        for(int x = 0; x < this.currentGameBoard.length; x++){
            for(int y = 0; y <this.currentGameBoard[x].length; y++){
                builder.append(String.format(this.currentGameBoard[x][y].toString()));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}

package com.engine.board;

import com.engine.*;
import com.engine.piece.*;
import com.engine.player.Player;
import com.engine.ui.UserInterface;

import javax.swing.*;
import java.util.ArrayList;

public class Board {

    private Tile[][] currentGameBoard;
    private Player player1;
    private Player player2;
    private GameStatus gameStatus;
    private Piece lastMovedPiece;
    private Alliance playerTurn;
    private Piece selectedPiece;
    private UserInterface userInterface;
    private Board futureBoard;

    public Board(boolean UI){
        this.gameStatus = GameStatus.INITIALIZING_GAME;
        this.player1 = new Player(this, Alliance.WHITE);
        this.player2 = new Player(this, Alliance.BLACK);
        this.player1.setEnemyPlayer(this.player2);
        this.player2.setEnemyPlayer(this.player1);
        this.currentGameBoard = defaultBoardSetup();
        this.playerTurn = Alliance.WHITE;
        this.gameStatus = GameStatus.PLAYER_TURN;
        this.userInterface = new UserInterface(this, UI);
        if(UI)
            futureBoard = new Board(false);
        else
            futureBoard = null;
    }

    public void restartGame(){
        this.gameStatus = GameStatus.INITIALIZING_GAME;
        this.player1 = new Player(this, Alliance.WHITE);
        this.player2 = new Player(this, Alliance.BLACK);
        this.player1.setEnemyPlayer(this.player2);
        this.player2.setEnemyPlayer(this.player1);
        this.currentGameBoard = defaultBoardSetup();
        this.playerTurn = Alliance.WHITE;
        this.gameStatus = GameStatus.PLAYER_TURN;
        futureBoard = new Board(false);
        refreshUI();
    }

    public void recalculatePlayerPieces(){
        ArrayList<Piece> player1Pieces = new ArrayList<>();
        ArrayList<Piece> player2Pieces = new ArrayList<>();
        for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
            for(int y = 0; y < GameUtils.GAME_BOARD_SIZE_WIDTH; y++){

                if(getTile(new int[]{x,y}) == null || getTile(new int[]{x,y}).getPiece() == null)
                    continue;

                Piece piece = getTile(new int[]{x,y}).getPiece();

                if(piece.getAlliance() == Alliance.WHITE)
                    player1Pieces.add(piece);
                if(piece.getAlliance() == Alliance.BLACK)
                    player2Pieces.add(piece);
            }
        }
        this.player1.setPlayerPieces(player1Pieces);
        this.player2.setPlayerPieces(player2Pieces);
    }

    public Board getFutureBoard(){return this.futureBoard;}

    public Player getPlayer1(){return this.player1;}
    public Player getPlayer2(){return this.player2;}

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

    public void refreshUI(){
        this.userInterface.getGameBoardPanel().deselectPiece();
        this.userInterface.getDataPanel().refreshDataPanel();
        this.userInterface.getGameBoardPanel().refreshTiles();
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
        setup[0][0] = new Tile(new int[]{0,0}, new Rook(this, Alliance.WHITE, this.player1, new int[]{0,0}));
        setup[0][1] = new Tile(new int[]{0,1}, new Knight(this, Alliance.WHITE, this.player1, new int[]{0,1}));
        setup[0][2] = new Tile(new int[]{0,2}, new Bishop(this, Alliance.WHITE, this.player1, new int[]{0,2}));
        setup[0][3] = new Tile(new int[]{0,3}, new Queen(this, Alliance.WHITE, this.player1, new int[]{0,3}));
        setup[0][4] = new Tile(new int[]{0,4}, new King(this, Alliance.WHITE, this.player1, new int[]{0,4}));
        setup[0][5] = new Tile(new int[]{0,5}, new Bishop(this, Alliance.WHITE, this.player1, new int[]{0,5}));
        setup[0][6] = new Tile(new int[]{0,6}, new Knight(this, Alliance.WHITE, this.player1, new int[]{0,6}));
        setup[0][7] = new Tile(new int[]{0,7}, new Rook(this, Alliance.WHITE, this.player1, new int[]{0,7}));
        setup[1][0] = new Tile(new int[]{1,0}, new Pawn(this, Alliance.WHITE, this.player1, new int[]{1,0}));
        setup[1][1] = new Tile(new int[]{1,1}, new Pawn(this, Alliance.WHITE, this.player1, new int[]{1,1}));
        setup[1][2] = new Tile(new int[]{1,2}, new Pawn(this, Alliance.WHITE, this.player1, new int[]{1,2}));
        setup[1][3] = new Tile(new int[]{1,3}, new Pawn(this, Alliance.WHITE, this.player1, new int[]{1,3}));
        setup[1][4] = new Tile(new int[]{1,4}, new Pawn(this, Alliance.WHITE, this.player1, new int[]{1,4}));
        setup[1][5] = new Tile(new int[]{1,5}, new Pawn(this, Alliance.WHITE, this.player1, new int[]{1,5}));
        setup[1][6] = new Tile(new int[]{1,6}, new Pawn(this, Alliance.WHITE, this.player1, new int[]{1,6}));
        setup[1][7] = new Tile(new int[]{1,7}, new Pawn(this, Alliance.WHITE, this.player1, new int[]{1,7}));


        /**
         * BLACK
         */
        setup[6][0] = new Tile(new int[]{6,0}, new Pawn(this, Alliance.BLACK, this.player2, new int[]{6,0}));
        setup[6][1] = new Tile(new int[]{6,1}, new Pawn(this, Alliance.BLACK, this.player2, new int[]{6,1}));
        setup[6][2] = new Tile(new int[]{6,2}, new Pawn(this, Alliance.BLACK, this.player2, new int[]{6,2}));
        setup[6][3] = new Tile(new int[]{6,3}, new Pawn(this, Alliance.BLACK, this.player2, new int[]{6,3}));
        setup[6][4] = new Tile(new int[]{6,4}, new Pawn(this, Alliance.BLACK, this.player2, new int[]{6,4}));
        setup[6][5] = new Tile(new int[]{6,5}, new Pawn(this, Alliance.BLACK, this.player2, new int[]{6,5}));
        setup[6][6] = new Tile(new int[]{6,6}, new Pawn(this, Alliance.BLACK, this.player2, new int[]{6,6}));
        setup[6][7] = new Tile(new int[]{6,7}, new Pawn(this, Alliance.BLACK, this.player2, new int[]{6,7}));
        setup[7][0] = new Tile(new int[]{7,0}, new Rook(this, Alliance.BLACK, this.player2, new int[]{7,0}));
        setup[7][1] = new Tile(new int[]{7,1}, new Knight(this, Alliance.BLACK, this.player2, new int[]{7,1}));
        setup[7][2] = new Tile(new int[]{7,2}, new Bishop(this, Alliance.BLACK, this.player2, new int[]{7,2}));
        setup[7][3] = new Tile(new int[]{7,3}, new Queen(this, Alliance.BLACK, this.player2, new int[]{7,3}));
        setup[7][4] = new Tile(new int[]{7,4}, new King(this, Alliance.BLACK, this.player2, new int[]{7,4}));
        setup[7][5] = new Tile(new int[]{7,5}, new Bishop(this, Alliance.BLACK, this.player2, new int[]{7,5}));
        setup[7][6] = new Tile(new int[]{7,6}, new Knight(this, Alliance.BLACK, this.player2, new int[]{7,6}));
        setup[7][7] = new Tile(new int[]{7,7}, new Rook(this, Alliance.BLACK, this.player2, new int[]{7,7}));


        return setup;
    }

    public void checkChessWinCondition(Player player){
        if(player.getPlayerPieces().size() < 2 && player.getEnemyPlayer().getPlayerPieces().size() < 2){
            JOptionPane.showMessageDialog(null, "Draw!",  "Game Over!", JOptionPane.WARNING_MESSAGE);
            System.out.println("Draw!");
            this.gameStatus = GameStatus.GAME_OVER;
            return;
        }
        if(player.kingUnderAttack()){
            if(player.getAllPlayerMoves().size() < 1){
                JOptionPane.showMessageDialog(null, "Checkmate! Player "+player.toString()+" lost the game! \n\n Start new game to play again!",  "Game Over!", JOptionPane.WARNING_MESSAGE);
                System.out.println("Checkmate! Player "+player.toString()+" lost the game!");
                this.gameStatus = GameStatus.GAME_OVER;
            }else{
                JOptionPane.showMessageDialog(null, "Check!",  "Check!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public boolean posInGameBoard(int[] pos){
        if(pos == null || pos.length != 2)
            return false;
        return (pos[0] >= 0 && pos[0] < GameUtils.GAME_BOARD_SIZE_HEIGHT && pos[1] >= 0 && pos[1] < GameUtils.GAME_BOARD_SIZE_WIDTH);
    }

    public Tile getTile(int[] pos){
        return currentGameBoard[pos[0]][pos[1]];
    }

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

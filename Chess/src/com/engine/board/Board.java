package com.engine.board;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.piece.*;
import com.engine.player.Player;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private Tile[][] gameBoard;
    private final Player p1;
    private final Player p2;

    public Board(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
        gameBoard = defaultBoardSetup();
    }

    public Tile[][] defaultBoardSetup(){
        Tile[][] setup = new Tile[GameUtils.GAME_BOARD_SIZE_HEIGHT][GameUtils.GAME_BOARD_SIZE_WIDTH];
        for(int x = 0; x < setup.length; x++){
            for(int y = 0; y < setup.length; y++){
                setup[x][y] = new Tile(new int[]{x, y});
            }
        }
        /**
         * WHITE
         */
        setup[0][0] = new Tile(new int[]{0,0}, new Rook(Alliance.WHITE, this.p1, new int[]{0,0}));
        setup[0][1] = new Tile(new int[]{0,1}, new Knight(Alliance.WHITE, this.p1, new int[]{0,1}));
        setup[0][2] = new Tile(new int[]{0,2}, new Bishop(Alliance.WHITE, this.p1, new int[]{0,2}));
        setup[0][3] = new Tile(new int[]{0,3}, new Queen(Alliance.WHITE, this.p1, new int[]{0,3}));
        setup[0][4] = new Tile(new int[]{0,4}, new King(Alliance.WHITE, this.p1, new int[]{0,4}));
        setup[0][5] = new Tile(new int[]{0,5}, new Bishop(Alliance.WHITE, this.p1, new int[]{0,5}));
        setup[0][6] = new Tile(new int[]{0,6}, new Knight(Alliance.WHITE, this.p1, new int[]{0,6}));
        setup[0][7] = new Tile(new int[]{0,7}, new Rook(Alliance.WHITE, this.p1, new int[]{0,7}));
        setup[1][0] = new Tile(new int[]{1,0}, new Pawn(Alliance.WHITE, this.p1, new int[]{1,0}));
        setup[1][1] = new Tile(new int[]{1,1}, new Pawn(Alliance.WHITE, this.p1, new int[]{1,1}));
        setup[1][2] = new Tile(new int[]{1,2}, new Pawn(Alliance.WHITE, this.p1, new int[]{1,2}));
        setup[1][3] = new Tile(new int[]{1,3}, new Pawn(Alliance.WHITE, this.p1, new int[]{1,3}));
        setup[1][4] = new Tile(new int[]{1,4}, new Pawn(Alliance.WHITE, this.p1, new int[]{1,4}));
        setup[1][5] = new Tile(new int[]{1,5}, new Pawn(Alliance.WHITE, this.p1, new int[]{1,5}));
        setup[1][6] = new Tile(new int[]{1,6}, new Pawn(Alliance.WHITE, this.p1, new int[]{1,6}));
        setup[1][7] = new Tile(new int[]{1,7}, new Pawn(Alliance.WHITE, this.p1, new int[]{1,7}));

        /**
         * BLACK
         */
        setup[6][0] = new Tile(new int[]{6,0}, new Pawn(Alliance.BLACK, this.p2, new int[]{6,0}));
        setup[6][1] = new Tile(new int[]{6,1}, new Pawn(Alliance.BLACK, this.p2, new int[]{6,1}));
        setup[6][2] = new Tile(new int[]{6,2}, new Pawn(Alliance.BLACK, this.p2, new int[]{6,2}));
        setup[6][3] = new Tile(new int[]{6,3}, new Pawn(Alliance.BLACK, this.p2, new int[]{6,3}));
        setup[6][4] = new Tile(new int[]{6,4}, new Pawn(Alliance.BLACK, this.p2, new int[]{6,4}));
        setup[6][5] = new Tile(new int[]{6,5}, new Pawn(Alliance.BLACK, this.p2, new int[]{6,5}));
        setup[6][6] = new Tile(new int[]{6,6}, new Pawn(Alliance.BLACK, this.p2, new int[]{6,6}));
        setup[6][7] = new Tile(new int[]{6,7}, new Pawn(Alliance.BLACK, this.p2, new int[]{6,7}));
        setup[7][0] = new Tile(new int[]{7,0}, new Rook(Alliance.BLACK, this.p2, new int[]{7,0}));
        setup[7][1] = new Tile(new int[]{7,1}, new Knight(Alliance.BLACK, this.p2, new int[]{7,1}));
        setup[7][2] = new Tile(new int[]{7,2}, new Bishop(Alliance.BLACK, this.p2, new int[]{7,2}));
        setup[7][3] = new Tile(new int[]{7,3}, new Queen(Alliance.BLACK, this.p2, new int[]{7,3}));
        setup[7][4] = new Tile(new int[]{7,4}, new King(Alliance.BLACK, this.p2, new int[]{7,4}));
        setup[7][5] = new Tile(new int[]{7,5}, new Bishop(Alliance.BLACK, this.p2, new int[]{7,5}));
        setup[7][6] = new Tile(new int[]{7,6}, new Knight(Alliance.BLACK, this.p2, new int[]{7,6}));
        setup[7][7] = new Tile(new int[]{7,7}, new Rook(Alliance.BLACK, this.p2, new int[]{7,7}));

        return setup;
    }

    public Tile getTile(int[] coords){
        return gameBoard[coords[0]][coords[1]];
    }


    @Override
    public String toString(){
        final StringBuilder builder = new StringBuilder();
        for(int x = 0; x < this.gameBoard.length; x++){
            for(int y = 0; y <this.gameBoard[x].length; y++){
                builder.append(String.format(this.gameBoard[x][y].toString()));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}

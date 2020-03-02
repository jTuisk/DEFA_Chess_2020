package com.engine;

import com.engine.board.Board;
import com.engine.board.Tile;
import com.engine.piece.Piece;
import com.engine.player.Player;
import com.engine.ui.UserInterface;

/**
 * https://www.youtube.com/watch?v=QDFI19lj4OM UNIT TEST
 * DRAW FUNCTION
 */

public class Game {

    public static void main(String[] args){
        GameUtils.GAME_STATUS = GameStatus.INITIALIZING_GAME;
        Player p1 = new Player(Alliance.WHITE);
        Player p2 = new Player(Alliance.BLACK);
        Board board = new Board(p1, p2);
        System.out.println(board.toString());
        UserInterface ui = new UserInterface(board, p1, p2);
        GameUtils.GAME_STATUS = GameStatus.PROMOTING_PAWN;
        //GameUtils.GAME_STATUS = GameStatus.WHITE_TURN;
    }
}
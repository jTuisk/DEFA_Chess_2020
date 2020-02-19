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

    public static Piece selectedPiece;

    public static void main(String[] args){
        Player p1 = new Player(Alliance.WHITE, GameUtils.SPEED_CHESS_TIME);
        Player p2 = new Player(Alliance.BLACK, GameUtils.SPEED_CHESS_TIME);

        Board board = new Board();
        System.out.println(board.toString());
        UserInterface ui = new UserInterface(board);

        System.out.println(board.getTile(new int[]{1,1}).getPiece().getAllAvailableMoves(board));
        System.out.println(board.getTile(new int[]{1,1}).getPiece().getAllAvailableMoves(board));
        System.out.println(board.getTile(new int[]{1,1}).getPiece().getAllAvailableMoves(board));
        System.out.println(board.getTile(new int[]{1,1}).getPiece().getAllAvailableMoves(board));

    }
}
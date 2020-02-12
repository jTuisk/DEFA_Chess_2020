package com.engine;

import com.engine.board.Board;
import com.engine.player.Player;

public class Game {

    public static void main(String[] args){
        Player p1 = new Player(Alliance.WHITE, GameUtils.SPEED_CHESS_TIME);
        Player p2 = new Player(Alliance.BLACK, GameUtils.SPEED_CHESS_TIME);

        Board board = new Board();
        System.out.println(board.toString());


        //System.out.println("---");
        //board.getTile(new int[]{0,1}).getPiece().getAvailableMoves(board);
        //System.out.println("---");
        //board.getTile(new int[]{3,2}).getPiece().getAvailableMoves(board);
        System.out.println("---");
        board.getTile(new int[]{0,3}).getPiece().getAvailableMoves(board);
        System.out.println("---");
        board.getTile(new int[]{1,3}).getPiece().getAvailableMoves(board);
        System.out.println("---");
        board.getTile(new int[]{6,5}).getPiece().getAvailableMoves(board);
    }
}

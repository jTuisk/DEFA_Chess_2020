package com.engine;

import com.engine.board.Board;
import com.engine.player.Player;

public class Game {

    public static void main(String[] args){
        Player p1 = new Player(Alliance.WHITE, GameUtils.SPEED_CHESS_TIME);
        Player p2 = new Player(Alliance.BLACK, GameUtils.SPEED_CHESS_TIME);

        Board board = new Board();
        System.out.println(board.toString());


        /**
         * ROOK TEST
         */
        System.out.println("---");
        //board.getTile(new int[]{2,3}).getPiece().getAvailableMoves(board);
        System.out.println(board.getTile(new int[]{2,3}).getPiece().canMove(board, new int[]{0,3}));
        System.out.println(board.getTile(new int[]{2,3}).getPiece().canMove(board, new int[]{1,3}));
        System.out.println(board.getTile(new int[]{2,3}).getPiece().canMove(board, new int[]{2,3}));
        System.out.println(board.getTile(new int[]{2,3}).getPiece().canMove(board, new int[]{3,3}));
        System.out.println(board.getTile(new int[]{2,3}).getPiece().canMove(board, new int[]{4,3}));
        System.out.println(board.getTile(new int[]{2,3}).getPiece().canMove(board, new int[]{5,3}));
        System.out.println(board.getTile(new int[]{2,3}).getPiece().canMove(board, new int[]{6,3}));
        System.out.println(board.getTile(new int[]{2,3}).getPiece().canMove(board, new int[]{7,3}));
        System.out.println("---");
        /*board.getTile(new int[]{3,2}).getPiece().getAvailableMoves(board);
        System.out.println("---");
        board.getTile(new int[]{4,5}).getPiece().getAvailableMoves(board);
        System.out.println("---");
        board.getTile(new int[]{3,5}).getPiece().getAvailableMoves(board);
        System.out.println("---");
        board.getTile(new int[]{0,0}).getPiece().getAvailableMoves(board);
        System.out.println("---");
        board.getTile(new int[]{0,7}).getPiece().getAvailableMoves(board);
        System.out.println("---");
        board.getTile(new int[]{7,0}).getPiece().getAvailableMoves(board);
        System.out.println("---");
        board.getTile(new int[]{7,7}).getPiece().getAvailableMoves(board);*/



        /**
         * PAWN TEST
         */
        //System.out.println("---");
        //board.getTile(new int[]{0,1}).getPiece().getAvailableMoves(board);
        //System.out.println("---");
        //board.getTile(new int[]{3,2}).getPiece().getAvailableMoves(board);
        //System.out.println("---");
        //board.getTile(new int[]{0,0}).getPiece().getAvailableMoves(board);
        //System.out.println("---");
        //board.getTile(new int[]{1,4}).getPiece().getAvailableMoves(board);
        //System.out.println("---");
        //board.getTile(new int[]{1,3}).getPiece().getAvailableMoves(board);
        //System.out.println("---");
        //board.getTile(new int[]{6,5}).getPiece().getAvailableMoves(board);

        /**
         * BISHOP TEST
         */
        //System.out.println("---");
        //board.getTile(new int[]{3,0}).getPiece().getAvailableMoves(board);
        //System.out.println("---");
        //board.getTile(new int[]{4,4}).getPiece().getAvailableMoves(board);
        //System.out.println("---");
        //board.getTile(new int[]{4,6}).getPiece().getAvailableMoves(board);
        //System.out.println("---");
        //board.getTile(new int[]{5,5}).getPiece().getAvailableMoves(board);

        /**
         * KNIGHT
         */
        //System.out.println("---");
        //board.getTile(new int[]{3,0}).getPiece().getAvailableMoves(board);
        //System.out.println("---");
        //board.getTile(new int[]{4,4}).getPiece().getAvailableMoves(board);
        //System.out.println("---");
        //board.getTile(new int[]{4,6}).getPiece().getAvailableMoves(board);
        //System.out.println("---");
        //board.getTile(new int[]{4,7}).getPiece().getAvailableMoves(board);
        //System.out.println("---");
        //board.getTile(new int[]{5,5}).getPiece().getAvailableMoves(board);

    }
}

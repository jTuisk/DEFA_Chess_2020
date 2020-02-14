package com.engine;

import com.engine.board.Board;
import com.engine.piece.Piece;
import com.engine.player.Player;

/**
 * https://www.youtube.com/watch?v=QDFI19lj4OM UNIT TEST
 * DRAW FUNCTION
 */

public class Game {

    public static void main(String[] args){
        Player p1 = new Player(Alliance.WHITE, GameUtils.SPEED_CHESS_TIME);
        Player p2 = new Player(Alliance.BLACK, GameUtils.SPEED_CHESS_TIME);

        Board board = new Board();
        System.out.println(board.toString());


        /**
         * ROOK TEST
         */
        //System.out.println("---");
        //System.out.println(board.getTile(new int[]{2,3}).getPiece().getAllAvailableMoves(board));//
        //System.out.println(Piece.canMove(board.getTile(new int[]{2,3}).getPiece().getAllAvailableMoves(board), new Piece.Move(board, board.getTile(new int[]{2,3}).getPiece(), new int[]{0,3})));
        //System.out.println(Piece.canMove(board.getTile(new int[]{2,3}).getPiece().getAllAvailableMoves(board), new Piece.Move(board, board.getTile(new int[]{2,3}).getPiece(), new int[]{1,3})));
        //System.out.println(Piece.canMove(board.getTile(new int[]{2,3}).getPiece().getAllAvailableMoves(board), new Piece.Move(board, board.getTile(new int[]{2,3}).getPiece(), new int[]{2,2})));
        //System.out.println(board.getTile(new int[]{2,3}).getPiece().canMove(board, new int[]{0,3}));
        //System.out.println(board.getTile(new int[]{2,3}).getPiece().canMove(board, new int[]{1,3}));
        //System.out.println(board.getTile(new int[]{2,3}).getPiece().canMove(board, new int[]{2,3}));
        //System.out.println(board.getTile(new int[]{2,3}).getPiece().canMove(board, new int[]{3,3}));
        //System.out.println(board.getTile(new int[]{2,3}).getPiece().canMove(board, new int[]{4,3}));
        //System.out.println(board.getTile(new int[]{2,3}).getPiece().canMove(board, new int[]{5,3}));
        //System.out.println(board.getTile(new int[]{2,3}).getPiece().canMove(board, new int[]{6,3}));
        //System.out.println(board.getTile(new int[]{2,3}).getPiece().canMove(board, new int[]{7,3}));
        //System.out.println("---");
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
        //System.out.println(board.getTile(new int[]{3,0}).getPiece().getAllAvailableMoves(board));
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
        //board.getTile(new int[]{3,0}).getPiece().getAllAvailableMoves(board);
        //System.out.println("---");
        //board.getTile(new int[]{4,4}).getPiece().getAvailableMoves(board);
        //System.out.println("---");
        //board.getTile(new int[]{4,6}).getPiece().getAvailableMoves(board);
        //System.out.println("---");
        //board.getTile(new int[]{4,7}).getPiece().getAvailableMoves(board);
        //System.out.println("---");
        //board.getTile(new int[]{5,5}).getPiece().getAvailableMoves(board);

        /**
         * QUEEN
         */

        System.out.println("---");
        System.out.println(board.getTile(new int[]{3,3}).getPiece().getAllAvailableMoves(board));
        System.out.println(board.getTile(new int[]{5,5}).getPiece().getAllAvailableMoves(board));
    }
}

/*


        setup[4][7] = new Tile(new int[]{4, 7}, new Queen(Alliance.BLACK, new int[]{4,7}));
        setup[5][5] = new Tile(new int[]{5, 5}, new Queen(Alliance.WHITE, new int[]{5,5}));
        setup[4][4] = new Tile(new int[]{4, 4}, new Queen(Alliance.WHITE, new int[]{4,4}));
        setup[3][3] = new Tile(new int[]{3, 3}, new Queen(Alliance.BLACK, new int[]{3,3}));
        setup[2][6] = new Tile(new int[]{2, 6}, new Queen(Alliance.WHITE, new int[]{2,6}));
 */
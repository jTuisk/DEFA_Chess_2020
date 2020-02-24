package com.engine;

import com.engine.board.Board;
import com.engine.piece.Piece;
import com.engine.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GameUtils {

    /**
     * GAME
     */
    public static Alliance PLAYER_TURN = Alliance.WHITE;
    public static void CHANGE_PLAYER_TURN(){
        PLAYER_TURN = PLAYER_TURN.isWhite() ? Alliance.BLACK : Alliance.WHITE;

    }

    public static Piece SELECTED_PIECE  = null;
    public static void selectPiece(Piece piece){
        if(piece != null && piece.getAlliance() == PLAYER_TURN)
            SELECTED_PIECE = piece;
    }

    /**
     *  PLAYER
     */
    public static final Time SPEED_CHESS_TIME = new Time(5,0,0);
    /**
     * Piece
     */
    public static ArrayList<Piece> PIECES_ONBOARD = new ArrayList<>();

    public static void removePieceFromList(Board board, Player player, int[] destPos){
        player.addLostPiece(board.getTile(destPos).getPiece());
        GameUtils.PIECES_ONBOARD.remove(board.getTile(destPos).getPiece());
        System.out.println(player.getLostPieces());
    }
    public static ArrayList<Piece.Move> GetAllEnemyMoves(Board board, Alliance alliance){
        ArrayList<Piece.Move> moves = new ArrayList<>();

        for(Piece piece : PIECES_ONBOARD){
            if(piece.getAlliance() == alliance)
                continue;
            for(Piece.Move move : piece.getAllAvailableMoves(board)){
                moves.add(move);
            }
        }
        System.out.println(moves.toString());
        return moves;
    }

    /**
     * GAME BOARD
     */
    public static final int GAME_BOARD_SIZE_WIDTH = 8;
    public static final int GAME_BOARD_SIZE_HEIGHT = 8;

    public static boolean coordsInGameBoard(int[] coords){
        if(coords == null || coords.length != 2)
            return false;
        return (coords[0] >= 0 && coords[0] < GAME_BOARD_SIZE_HEIGHT && coords[1] >= 0 && coords[1] < GAME_BOARD_SIZE_WIDTH);
    }

    /**
     * GAME GUI
     */
    public static final Dimension GUI_FRAME_SIZE = new Dimension(800, 650);
    public static final Dimension BOARD_FRAME_SIZE = new Dimension(600,650);
    public static final Dimension DATA_FRAME_SIZE = new Dimension(200,650);
    public static final Dimension PAWN_PROMOTION_LABEL_SIZE = new Dimension(400, 200);
    public static final Dimension SINGLE_TILE_SIZE = new Dimension(65,65);
    public static final Color BOARD_FRAME_COLOR = new Color(232, 232, 232);
    public static final Color DARK_TILE_COLOR = new Color(125, 135, 150);
    public static final Color LIGHT_TILE_COLOR = new Color(232, 235, 239);
    public static final Color MOVABLE_TILE_BORDER_COLOR = new Color(22, 204, 71);
    public static final Color SELECTED_TILE_COLOR = new Color(62, 208, 247);
    public static final Color TILE_UNDER_ATTACK_COLOR = new Color(252, 169, 3);
    public static final Color KING_UNDER_ATTACK_COLOR = new Color(201, 27, 4);
    public static final Point GUI_FRAME_POSITION = new Point((Toolkit.getDefaultToolkit().getScreenSize().width/2) - (GUI_FRAME_SIZE.width/2),
                                                                    (Toolkit.getDefaultToolkit().getScreenSize().height/2) - (GUI_FRAME_SIZE.height/2));

    public static final String[] GAME_BOARD_VERTICAL_TILE_COORDINATES = new String[] { "1", "2", "3", "4", "5", "6", "7", "8"};
    public static final String[] GAME_BOARD_HORIZONTAL_TILE_COORDINATES = new String[] { "A", "B", "C", "D", "E", "F", "G", "H"};
}

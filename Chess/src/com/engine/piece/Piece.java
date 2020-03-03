package com.engine.piece;

import com.engine.Alliance;
import com.engine.GameStatus;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;
import com.engine.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Piece {

    protected final Player player;
    protected final Alliance alliance;
    protected final PieceType pieceType;
    protected int[] piecePosition;
    protected boolean firstMove;

    Piece(Alliance alliance, Player player, int[] piecePosition, PieceType pieceType){
        this.player = player;
        this.alliance = alliance;
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        firstMove = true;
        GameUtils.PIECES_ONBOARD.add(this);
    }

    public static boolean canMove(List<Move> moves, Move destinationMove){
        for(Move move : moves){
            if(move.equals(destinationMove))
                return true;
        }
        return false;
    }

    public abstract List<Move> getAllAvailableMoves(Board board);

    public void setPiecePosition(int[] piecePosition){
        this.piecePosition = piecePosition;
    }

    public int[] getPosition(){
        return this.piecePosition;
    }

    public Alliance getAlliance(){
        return this.alliance;
    }

    public PieceType getPieceType(){
        return this.pieceType;
    }

    protected List<Move> getVerticalMovesDown(Board board, int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; ((this.piecePosition[1]+x)-GameUtils.GAME_BOARD_SIZE_HEIGHT) < 0; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0], this.piecePosition[1]+x};

            if(!GameUtils.coordsInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;

            Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();

            if(pieceAtDestination != null){
                if(pieceAtDestination.getAlliance() == this.getAlliance()){
                    break;
                }
                moves.add(new Move(board, this, pieceAtDestination.getPosition()));
                break;
            }
            moves.add(new Move(board, this, destinationPosition));
        }
        return moves;
    }

    protected List<Move> getVerticalMovesUp(Board board, int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; (this.piecePosition[1]-x) > -1; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0], this.piecePosition[1]-x};

            if(!GameUtils.coordsInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;

            Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();
            if(pieceAtDestination != null){
                if(pieceAtDestination.getAlliance() == this.getAlliance()){
                    break;
                }
                moves.add(new Move(board, this, pieceAtDestination.getPosition()));
                break;
            }
            moves.add(new Move(board, this, destinationPosition));
        }
        return moves;
    }

    protected List<Move> getHorizontalMovesRight(Board board, int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; ((this.piecePosition[0]+x)-GameUtils.GAME_BOARD_SIZE_WIDTH) < 0; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0]+x, this.piecePosition[1]};

            if(!GameUtils.coordsInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;

            Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();

            if(pieceAtDestination != null){
                if(pieceAtDestination.getAlliance() == this.getAlliance()){
                    break;
                }
                moves.add(new Move(board, this, pieceAtDestination.getPosition()));
                break;
            }
            moves.add(new Move(board, this, destinationPosition));
        }
        return moves;
    }

    protected List<Move> getHorizontalMovesLeft(Board board, int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; (this.piecePosition[0]-x) > -1; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0]-x, this.piecePosition[1]};

            if(!GameUtils.coordsInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;

            Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();

            if(pieceAtDestination != null){
                if(pieceAtDestination.getAlliance() == this.getAlliance()){
                    break;
                }
                moves.add(new Move(board, this, pieceAtDestination.getPosition()));
                break;
            }
            moves.add(new Move(board, this, destinationPosition));
        }
        return moves;
    }

    protected List<Move> getDiagonalMovesRightDown(Board board, int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; ((this.piecePosition[1]+x)-GameUtils.GAME_BOARD_SIZE_HEIGHT) < 0; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0]+x, this.piecePosition[1]+x};

            if(!GameUtils.coordsInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;

            Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();

            if(pieceAtDestination != null){
                if(pieceAtDestination.getAlliance() == this.getAlliance()){
                    break;
                }
                moves.add(new Move(board, this, pieceAtDestination.getPosition()));
                break;
            }
            moves.add(new Move(board, this, destinationPosition));
        }
        return moves;
    }

    protected List<Move> getDiagonalMovesLeftUp(Board board, int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; (this.piecePosition[1]-x) > -1; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0]-x, this.piecePosition[1]-x};

            if(!GameUtils.coordsInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;

            Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();
            if(pieceAtDestination != null){
                if(pieceAtDestination.getAlliance() == this.getAlliance()){
                    break;
                }
                moves.add(new Move(board, this, pieceAtDestination.getPosition()));
                break;
            }
            moves.add(new Move(board, this, destinationPosition));
        }
        return moves;
    }

    protected List<Move> getDiagonalMovesRightUp(Board board, int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; (this.piecePosition[1]-x) > -1; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0]+x, this.piecePosition[1]-x};

            if(!GameUtils.coordsInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;

            Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();
            if(pieceAtDestination != null){
                if(pieceAtDestination.getAlliance() == this.getAlliance()){
                    break;
                }
                moves.add(new Move(board, this, pieceAtDestination.getPosition()));
                break;
            }
            moves.add(new Move(board, this, destinationPosition));
        }
        return moves;
    }

    protected List<Move> getDiagonalMovesLeftDown(Board board, int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; ((this.piecePosition[1]+x)-GameUtils.GAME_BOARD_SIZE_HEIGHT) < 0; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0]-x, this.piecePosition[1]+x};

            if(!GameUtils.coordsInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;

            Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();

            if(pieceAtDestination != null){
                if(pieceAtDestination.getAlliance() == this.getAlliance()){
                    break;
                }
                moves.add(new Move(board, this, pieceAtDestination.getPosition()));
                break;
            }
            moves.add(new Move(board, this, destinationPosition));
        }
        return moves;
    }

    protected boolean kingUnderFireAfterMove(Board board, int[] moveToPos){

        GameUtils.GetAllEnemyMoves(board, this.alliance);

        return false;
    }


    public Player getPlayer(){
        return this.player;
    }

    public void finishMove(Board board, int[] destCoords){
        if(GameUtils.GAME_STATUS != GameStatus.PLAYER_TURN)
            return;

        if(board.getTile(destCoords).getPiece() != null){
            Player enemy = board.getTile(destCoords).getPiece().getPlayer();
            GameUtils.removePieceFromList(board, enemy, destCoords);
        }
        GameUtils.PIECES_ONBOARD.remove(board.getTile(destCoords).getPiece());
        board.getTile(destCoords).setPiece(GameUtils.SELECTED_PIECE);
        board.getTile(GameUtils.SELECTED_PIECE.getPosition()).setPiece(null);
        GameUtils.SELECTED_PIECE.setPiecePosition(destCoords);
        this.firstMove = false;
        GameUtils.LAST_MOVED_PIECE = this;
        GameUtils.CHANGE_PLAYER_TURN();
    }

    @Override
    public String toString(){
        return pieceType.toString();
    }

    public static class Move {

        final Board board;
        final Piece piece;
        final int[] destCoords;
        final Piece attackedPiece;

        public Move(Board board, Piece piece, int[] destCoords){
            this.board = board;
            this.piece = piece;
            this.destCoords = destCoords;
            this.attackedPiece = board.getTile(destCoords).getPiece();
        }

        public int[] getDestCoords(){
            return this.destCoords;
        }

        @Override
        public boolean equals(Object o){
            if(!(o instanceof  Move))
                return false;
            return this.destCoords[0] == ((Move) o).destCoords[0] && this.destCoords[1] == ((Move) o).destCoords[1];
        }

        @Override
        public String toString(){
            return piece+": "+ Arrays.toString(destCoords);
        }
    }
}

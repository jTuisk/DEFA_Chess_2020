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

    protected final Board board;
    protected final Player player;
    protected final Alliance alliance;
    protected final PieceType pieceType;
    protected int[] piecePosition;
    protected boolean firstMove;

    Piece(Board board, Alliance alliance, Player player, int[] piecePosition, PieceType pieceType){
        this.board = board;
        this.player = player;
        this.alliance = alliance;
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        firstMove = true;
        board.getPiecesOnBoard().add(this);
    }

    public Player getPlayer(){
        return this.player;
    }

    public static boolean canMove(List<Move> moves, Move destinationMove){
        for(Move move : moves){
            if(move.equals(destinationMove))
                return true;
        }
        return false;
    }

    public abstract List<Move> getAllAvailableMoves();

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

    protected List<Move> getVerticalMovesDown(int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; ((this.piecePosition[1]+x)-GameUtils.GAME_BOARD_SIZE_HEIGHT) < 0; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0], this.piecePosition[1]+x};

            if(!this.board.posInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;

            Piece pieceAtDestination = this.board.getTile(destinationPosition).getPiece();

            if(pieceAtDestination != null){
                if(pieceAtDestination.getAlliance() == this.getAlliance()){
                    break;
                }
                moves.add(new Move(this.board, this, pieceAtDestination.getPosition()));
                break;
            }
            moves.add(new Move(this.board, this, destinationPosition));
        }
        return moves;
    }

    protected List<Move> getVerticalMovesUp(int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; (this.piecePosition[1]-x) > -1; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0], this.piecePosition[1]-x};

            if(!this.board.posInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;

            Piece pieceAtDestination = this.board.getTile(destinationPosition).getPiece();
            if(pieceAtDestination != null){
                if(pieceAtDestination.getAlliance() == this.getAlliance()){
                    break;
                }
                moves.add(new Move(this.board, this, pieceAtDestination.getPosition()));
                break;
            }
            moves.add(new Move(this.board, this, destinationPosition));
        }
        return moves;
    }

    protected List<Move> getHorizontalMovesRight(int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; ((this.piecePosition[0]+x)-GameUtils.GAME_BOARD_SIZE_WIDTH) < 0; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0]+x, this.piecePosition[1]};

            if(!this.board.posInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;

            Piece pieceAtDestination = this.board.getTile(destinationPosition).getPiece();

            if(pieceAtDestination != null){
                if(pieceAtDestination.getAlliance() == this.getAlliance()){
                    break;
                }
                moves.add(new Move(this.board, this, pieceAtDestination.getPosition()));
                break;
            }
            moves.add(new Move(this.board, this, destinationPosition));
        }
        return moves;
    }

    protected List<Move> getHorizontalMovesLeft(int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; (this.piecePosition[0]-x) > -1; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0]-x, this.piecePosition[1]};

            if(!this.board.posInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;

            Piece pieceAtDestination = this.board.getTile(destinationPosition).getPiece();

            if(pieceAtDestination != null){
                if(pieceAtDestination.getAlliance() == this.getAlliance()){
                    break;
                }
                moves.add(new Move(this.board, this, pieceAtDestination.getPosition()));
                break;
            }
            moves.add(new Move(this.board, this, destinationPosition));
        }
        return moves;
    }

    protected List<Move> getDiagonalMovesRightDown(int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; ((this.piecePosition[1]+x)-GameUtils.GAME_BOARD_SIZE_HEIGHT) < 0; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0]+x, this.piecePosition[1]+x};

            if(!this.board.posInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;

            Piece pieceAtDestination = this.board.getTile(destinationPosition).getPiece();

            if(pieceAtDestination != null){
                if(pieceAtDestination.getAlliance() == this.getAlliance()){
                    break;
                }
                moves.add(new Move(this.board, this, pieceAtDestination.getPosition()));
                break;
            }
            moves.add(new Move(this.board, this, destinationPosition));
        }
        return moves;
    }

    protected List<Move> getDiagonalMovesLeftUp(int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; (this.piecePosition[1]-x) > -1; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0]-x, this.piecePosition[1]-x};

            if(!this.board.posInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;

            Piece pieceAtDestination = this.board.getTile(destinationPosition).getPiece();
            if(pieceAtDestination != null){
                if(pieceAtDestination.getAlliance() == this.getAlliance()){
                    break;
                }
                moves.add(new Move(this.board, this, pieceAtDestination.getPosition()));
                break;
            }
            moves.add(new Move(this.board, this, destinationPosition));
        }
        return moves;
    }

    protected List<Move> getDiagonalMovesRightUp(int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; (this.piecePosition[1]-x) > -1; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0]+x, this.piecePosition[1]-x};

            if(!this.board.posInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;

            Piece pieceAtDestination = this.board.getTile(destinationPosition).getPiece();

            if(pieceAtDestination != null){
                if(pieceAtDestination.getAlliance() == this.getAlliance()){
                    break;
                }
                moves.add(new Move(this.board, this, pieceAtDestination.getPosition()));
                break;
            }
            moves.add(new Move(this.board, this, destinationPosition));
        }
        return moves;
    }

    protected List<Move> getDiagonalMovesLeftDown(int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; ((this.piecePosition[1]+x)-GameUtils.GAME_BOARD_SIZE_HEIGHT) < 0; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0]-x, this.piecePosition[1]+x};

            if(!this.board.posInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;


            Piece pieceAtDestination = this.board.getTile(destinationPosition).getPiece();

            if(pieceAtDestination != null){
                if(pieceAtDestination.getAlliance() == this.getAlliance()){
                    break;
                }
                moves.add(new Move(this.board, this, pieceAtDestination.getPosition()));
                break;
            }
            moves.add(new Move(board, this, destinationPosition));
        }
        return moves;
    }

   public void finishMove(int[] destPos){
        if( this.board.getGameStatus() != GameStatus.PLAYER_TURN ||
            this.board.getPlayerTurn() != this.alliance)
            return;

        if(board.getTile(destPos).getPiece() != null){
            Player enemy = board.getTile(destPos).getPiece().getPlayer();
            this.board.removePieceFromList(enemy, destPos);
        }
       this.board.getPiecesOnBoard().remove(board.getTile(destPos).getPiece());
       this.board.getTile(destPos).setPiece(this.board.getSelectedPiece());
       this.board.getTile(this.board.getSelectedPiece().getPosition()).setPiece(null);
       this.board.getSelectedPiece().setPiecePosition(destPos);
       this.board.setLastMovedPiece(this);
       this.board.changePlayerTurn();
       this.firstMove = false;
       this.board.refreshUI();
    }

    @Override
    public String toString(){
        return pieceType.toString();
    }

    public static class Move {

        final Board board;
        final Piece piece;
        final int[] destPos;
        final Piece attackedPiece;

        public Move(Board board, Piece piece, int[] desPos){
            this.board = board;
            this.piece = piece;
            this.destPos = desPos;
            this.attackedPiece = board.getTile(desPos).getPiece();
        }

        public Piece getPiece(){return this.piece;}

        public int[] getDestCoords(){
            return this.destPos;
        }

        @Override
        public boolean equals(Object o){
            if(!(o instanceof  Move))
                return false;
            return this.destPos[0] == ((Move) o).destPos[0] && this.destPos[1] == ((Move) o).destPos[1];
        }

        @Override
        public String toString(){
            return piece+": "+ Arrays.toString(destPos);
        }
    }
}

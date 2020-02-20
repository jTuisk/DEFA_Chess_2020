package com.engine.piece;

import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    //Castling - https://simple.wikipedia.org/wiki/Castling

    public King(Alliance alliance, int[] piecePosition) {
        super(alliance, piecePosition, PieceType.KING);
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

    @Override
    public List<Move> getAllAvailableMoves(Board board){
        ArrayList<Move> moves = new ArrayList<>();

        for(Move move : this.getDiagonalMovesRightDown(board, 1)){
            moves.add(move);
        }
        for(Move move : this.getDiagonalMovesLeftDown(board, 1)){
            moves.add(move);
        }
        for(Move move : this.getDiagonalMovesLeftUp(board, 1)){
            moves.add(move);
        }
        for(Move move : this.getDiagonalMovesRightUp(board, 1)){
            moves.add(move);
        }
        for(Move move : this.getVerticalMovesUp(board, 1)){
            moves.add(move);
        }
        for(Move move : this.getVerticalMovesDown(board, 1)){
            moves.add(move);
        }
        for(Move move : this.getHorizontalMovesLeft(board, 1)){
            moves.add(move);
        }
        for(Move move : this.getHorizontalMovesRight(board, 1)){
            moves.add(move);
        }
        return moves;
    }
}

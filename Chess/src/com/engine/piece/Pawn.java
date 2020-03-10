package com.engine.piece;


import com.engine.Alliance;
import com.engine.GameStatus;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;
import com.engine.player.Player;
import com.engine.ui.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Board board,Alliance alliance, Player player, int[] piecePosition) {
        super(board, alliance, player, piecePosition, PieceType.PAWN);
    }

    @Override
    protected List<Move> getHorizontalMovesRight(int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; ((this.piecePosition[0]+x)-GameUtils.GAME_BOARD_SIZE_WIDTH) < 0; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0]+x, this.piecePosition[1]};

            if(!this.board.posInGameBoard(destinationPosition) || moves.size() >= maxTiles )
                break;

            Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();

            if(pieceAtDestination != null)
                break;

            moves.add(new Move(board, this, destinationPosition));
        }
        return moves;
    }

    @Override
    protected List<Move> getHorizontalMovesLeft( int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; (this.piecePosition[0]-x) > -1; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0]-x, this.piecePosition[1]};

            if(!this.board.posInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;

            Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();

            if(pieceAtDestination != null)
                break;

            moves.add(new Move(board, this, destinationPosition));
        }
        return moves;
    }

    @Override
    protected List<Move> getDiagonalMovesRightDown(int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();
        int[] destinationPosition = new int[]{this.piecePosition[0]+1, this.piecePosition[1]+1};


        if (!this.board.posInGameBoard(destinationPosition))
            return moves;

        Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();
        if (pieceAtDestination != null && pieceAtDestination.getAlliance() != this.getAlliance())
            moves.add(new Move(board, this, pieceAtDestination.getPosition()));

        return moves;
    }

    @Override
    protected List<Move> getDiagonalMovesLeftUp(int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();
        int[] destinationPosition = new int[]{this.piecePosition[0]-1, this.piecePosition[1]-1};

        if (!this.board.posInGameBoard(destinationPosition))
            return moves;

        Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();
        if (pieceAtDestination != null && pieceAtDestination.getAlliance() != this.getAlliance())
            moves.add(new Move(board, this, pieceAtDestination.getPosition()));

        return moves;
    }

    @Override
    protected List<Move> getDiagonalMovesRightUp(int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();
        int[] destinationPosition = new int[]{this.piecePosition[0] + 1, this.piecePosition[1] - 1};

        if (!this.board.posInGameBoard(destinationPosition))
            return moves;

        Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();
        if (pieceAtDestination != null && pieceAtDestination.getAlliance() != this.getAlliance())
            moves.add(new Move(board, this, pieceAtDestination.getPosition()));

        return moves;
    }

    @Override
    protected List<Move> getDiagonalMovesLeftDown(int maxTiles) {
        ArrayList<Move> moves = new ArrayList<>();

        int[] destinationPosition = new int[]{this.piecePosition[0] - 1, this.piecePosition[1] + 1};

        if (!this.board.posInGameBoard(destinationPosition))
            return moves;

        Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();
        if (pieceAtDestination != null && pieceAtDestination.getAlliance() != this.getAlliance())
            moves.add(new Move(board, this, pieceAtDestination.getPosition()));


        return moves;
    }

    @Override
    public List<Move> getAllAvailableMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        int maxTiles = this.firstMove ? 2 : 1;
        if(this.getAlliance().isWhite()){
            for(Move move : this.getDiagonalMovesRightDown(1)){
                if(canMove(move.getDestCoords()))
                    moves.add(move);
            }
            for(Move move : this.getDiagonalMovesRightUp(1)){
                if(canMove(move.getDestCoords()))
                moves.add(move);
            }
            for(Move move : this.getHorizontalMovesRight(maxTiles)){
                if(canMove(move.getDestCoords()))
                    moves.add(move);
            }
        }
        if(this.getAlliance().isBlack()){
            for(Move move : this.getDiagonalMovesLeftDown(1)){
                if(canMove(move.getDestCoords()))
                    moves.add(move);
            }
            for(Move move : this.getDiagonalMovesLeftUp(1)){
                if(canMove(move.getDestCoords()))
                    moves.add(move);
            }
            for(Move move : this.getHorizontalMovesLeft(maxTiles)){
                if(canMove(move.getDestCoords()))
                    moves.add(move);
            }
        }

        return moves;
    }

    @Override
    public void finishMove(int[] destPos){
        if(this.board.getGameStatus() != GameStatus.PLAYER_TURN || this.board.getPlayerTurn() != this.alliance)
            return;

        Board f_board = this.board.getFutureBoard();
        Piece f_currentPiece = f_board.getTile(this.piecePosition).getPiece();

        if(this.board.getTile(destPos).getPiece() != null){
            f_currentPiece.getPlayer().getEnemyPlayer().removePieceFromPlayer(f_board.getTile(destPos).getPiece());
            this.player.getEnemyPlayer().removePieceFromPlayer(this.board.getTile(destPos).getPiece());
        }

        f_board.getTile(destPos).setPiece(f_currentPiece);
        f_currentPiece.setPiecePosition(destPos);
        f_board.getTile(this.piecePosition).setPiece(null);
        this.board.getTile(destPos).setPiece(this);
        this.board.getTile(this.piecePosition).setPiece(null);
        this.piecePosition = destPos;
        this.board.setLastMovedPiece(this);

        if(this.getAlliance().isWhite()){
            for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
                if(this.getPosition()[0] == 7 && this.getPosition()[1] == x){
                    this.board.setGameStatus(GameStatus.PROMOTING_PAWN);
                    break;
                }
            }
        }
        if(this.getAlliance().isBlack()){
            for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
                if(this.getPosition()[0] == 0 && this.getPosition()[1] == x){
                    this.board.setGameStatus(GameStatus.PROMOTING_PAWN);
                    break;
                }
            }
        }
        this.board.setSelectPiece(null);
        this.board.changePlayerTurn();
        this.firstMove = false;
        this.board.refreshUI();
        this.board.checkChessWinCondition(this.player.getEnemyPlayer());
    }
}

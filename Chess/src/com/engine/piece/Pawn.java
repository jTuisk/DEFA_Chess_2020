package com.engine.piece;


import com.engine.Alliance;
import com.engine.GameUtils;
import com.engine.PieceType;
import com.engine.board.Board;
import com.engine.player.Player;
import com.engine.ui.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Alliance alliance, Player player, int[] piecePosition) {
        super(alliance, player, piecePosition, PieceType.PAWN);
    }


    public void promotePawn(Board board, Piece toPiece){
        board.getTile(this.getPosition()).setPiece(toPiece);
    }

    @Override
    protected List<Move> getHorizontalMovesRight(Board board, int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; ((this.piecePosition[0]+x)-GameUtils.GAME_BOARD_SIZE_WIDTH) < 0; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0]+x, this.piecePosition[1]};

            if(!GameUtils.coordsInGameBoard(destinationPosition) || moves.size() >= maxTiles )
                break;

            Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();

            if(pieceAtDestination != null)
                break;

            moves.add(new Move(board, this, destinationPosition));
        }
        return moves;
    }

    @Override
    protected List<Move> getHorizontalMovesLeft(Board board, int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();

        for(int x = 1; (this.piecePosition[0]-x) > -1; x++){
            int[] destinationPosition = new int[]{this.piecePosition[0]-x, this.piecePosition[1]};

            if(!GameUtils.coordsInGameBoard(destinationPosition) || moves.size() >= maxTiles)
                break;

            Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();

            if(pieceAtDestination != null)
                break;

            moves.add(new Move(board, this, destinationPosition));
        }
        return moves;
    }

    @Override
    protected List<Move> getDiagonalMovesRightDown(Board board, int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();
        int[] destinationPosition = new int[]{this.piecePosition[0]+1, this.piecePosition[1]+1};


        if (!GameUtils.coordsInGameBoard(destinationPosition))
            return moves;

        Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();
        if (pieceAtDestination != null && pieceAtDestination.getAlliance() != this.getAlliance())
            moves.add(new Move(board, this, pieceAtDestination.getPosition()));

        return moves;
    }

    @Override
    protected List<Move> getDiagonalMovesLeftUp(Board board, int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();
        int[] destinationPosition = new int[]{this.piecePosition[0]-1, this.piecePosition[1]-1};

        if (!GameUtils.coordsInGameBoard(destinationPosition))
            return moves;

        Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();
        if (pieceAtDestination != null && pieceAtDestination.getAlliance() != this.getAlliance())
            moves.add(new Move(board, this, pieceAtDestination.getPosition()));

        return moves;
    }

    @Override
    protected List<Move> getDiagonalMovesRightUp(Board board, int maxTiles){
        ArrayList<Move> moves = new ArrayList<>();
        int[] destinationPosition = new int[]{this.piecePosition[0] + 1, this.piecePosition[1] - 1};

        if (!GameUtils.coordsInGameBoard(destinationPosition))
            return moves;

        Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();
        if (pieceAtDestination != null && pieceAtDestination.getAlliance() != this.getAlliance())
            moves.add(new Move(board, this, pieceAtDestination.getPosition()));

        return moves;
    }

    @Override
    protected List<Move> getDiagonalMovesLeftDown(Board board, int maxTiles) {
        ArrayList<Move> moves = new ArrayList<>();

        int[] destinationPosition = new int[]{this.piecePosition[0] - 1, this.piecePosition[1] + 1};

        if (!GameUtils.coordsInGameBoard(destinationPosition))
            return moves;

        Piece pieceAtDestination = board.getTile(destinationPosition).getPiece();
        if (pieceAtDestination != null && pieceAtDestination.getAlliance() != this.getAlliance())
            moves.add(new Move(board, this, pieceAtDestination.getPosition()));


        return moves;
    }

    @Override
    public List<Move> getAllAvailableMoves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        int maxTiles = this.firstMove ? 2 : 1;
        if(this.getAlliance().isWhite()){
            for(Move move : this.getDiagonalMovesRightDown(board, 1)){
                moves.add(move);
            }
            for(Move move : this.getDiagonalMovesRightUp(board, 1)){
                moves.add(move);
            }
            for(Move move : this.getHorizontalMovesRight(board, maxTiles)){
                moves.add(move);
            }
        }
        if(this.getAlliance().isBlack()){
            for(Move move : this.getDiagonalMovesLeftDown(board, 1)){
                moves.add(move);
            }
            for(Move move : this.getDiagonalMovesLeftUp(board, 1)){
                moves.add(move);
            }
            for(Move move : this.getHorizontalMovesLeft(board, maxTiles)){
                moves.add(move);
            }
        }

        return moves;
    }

    @Override
    public void finishMove(Board board, int[] destCoords){
        if(board.getTile(destCoords).getPiece() != null){
            Player enemy = board.getTile(destCoords).getPiece().getPlayer();
            GameUtils.removePieceFromList(board, enemy, destCoords);
        }
        GameUtils.PIECES_ONBOARD.remove(board.getTile(destCoords).getPiece());
        board.getTile(destCoords).setPiece(GameUtils.SELECTED_PIECE);
        board.getTile(GameUtils.SELECTED_PIECE.getPosition()).setPiece(null);
        GameUtils.SELECTED_PIECE.setPiecePosition(destCoords);

        if(this.getAlliance().isWhite()){
            for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
                if(this.getPosition()[0] == 7 && this.getPosition()[1] == x){
                    UserInterface.showPromotionPanel(this);
                    break;
                }
            }
        }
        if(this.getAlliance().isBlack()){
            for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
                if(this.getPosition()[0] == 0 && this.getPosition()[1] == x){
                    UserInterface.showPromotionPanel(this);
                    break;
                }
            }
        }
        this.firstMove = false;
        GameUtils.CHANGE_PLAYER_TURN();
    }
}

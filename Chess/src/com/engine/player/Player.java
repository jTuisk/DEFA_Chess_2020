package com.engine.player;

import com.engine.Alliance;
import com.engine.PieceType;
import com.engine.board.Board;
import com.engine.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final Board board;
    private final Alliance alliance;
    private List<Piece> playerPieces;
    private List<Piece> lostPieces;
    private Player enemyPlayer;

    public Player(Board board, Alliance alliance){
        this.board = board;
        this.playerPieces = new ArrayList<>();
        this.lostPieces = new ArrayList<>();
        this.alliance = alliance;
    }

    public Player getEnemyPlayer(){return this.enemyPlayer;}
    public void setEnemyPlayer(Player enemyPlayer){this.enemyPlayer = enemyPlayer;}

    public void addPieceToPlayer(Piece piece){
        this.playerPieces.add(piece);
        if(this.lostPieces.contains(piece))
            this.lostPieces.remove(piece);
    }

    public void removePieceFromPlayer(Piece piece){
        this.lostPieces.add(piece);
        if(this.playerPieces.contains(piece))
            this.playerPieces.remove(piece);
    }

    public void removePieceFromPlayer(Piece piece, boolean promote){
        if(!promote)
            this.lostPieces.add(piece);
        this.playerPieces.remove(piece);
    }

    public void setLostPieces(List<Piece> lostPieces){
        this.lostPieces = lostPieces;
    }

    public void setPlayerPieces(List<Piece> playerPieces){
        this.playerPieces = playerPieces;
    }

    public List<Piece> getPlayerPieces(){
        return this.playerPieces;
    }

    public boolean kingUnderAttack(){
        if(this.board.getFutureBoard() == null){
            System.out.println("f_Player pieces: "+getPlayerPieces().toString()+" "+getPlayerPieces().size());
            System.out.println("f_Enemy pieces: "+enemyPlayer.getPlayerPieces().toString()+" "+getPlayerPieces().size());
        }
        System.out.println();
        System.out.println();
        for(Piece.Move  move : this.enemyPlayer.getAllPlayerMoves()){
            Piece piece = this.board.getTile(move.getDestCoords()).getPiece();
            if (piece == null)
                continue;

            if (piece.getPieceType() == PieceType.KING && piece.getAlliance() == this.alliance) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Piece.Move> getAllPlayerMoves(){
        ArrayList<Piece.Move> moves = new ArrayList<>();
        for(Piece piece : this.playerPieces){
            moves.addAll(piece.getAllAvailableMoves());
        }

        return moves;
    }

    public Alliance getAlliance() {
        return this.alliance;
    }

    public List<Piece> getLostPieces(){
        return this.lostPieces;
    }

    @Override
    public String toString(){
        return ""+this.alliance;
    }
}

import pieces.*;

public class Tile {

    Piece piece;

    public Tile(Piece piece){
        this.piece = piece;
    }

    public Piece getPiece(){return this.piece;}
    public void setPiece(Piece piece){this.piece = piece;}
    public boolean hasPiece() {return this.piece != null;}
    public void removePiece(){this.piece = null;}


}

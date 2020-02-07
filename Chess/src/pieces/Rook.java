package pieces;

public class Rook extends Piece {

    public Rook(int posX, int posY, Color color){
        super(posX, posY, color);
    }

    @Override
    public boolean canMove(int desX, int desY) {
        //Can move only one way forwards, backwards, left and right.
        //Can't move over own pieces. So has to stop tile before own piece.
        //Can move to enemy piece(Eat) but not over it.

        return true;
    }
}

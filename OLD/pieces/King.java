package pieces;

public class King extends Piece {

    public King(int posX, int posY, Color color){
        super(posX, posY, color);
    }

    @Override
    public boolean canMove(int desX, int desY) {
        //Can't move over own pieces. So has to stop tile before own piece.
        //Can move to enemy piece(Eat) but not over it.
        //Can't move to under enemy fire.
        //Can only move one tile at the time and direction

        return true;
    }
}
package pieces;

public class Knight extends Piece {

    public Knight(int posX, int posY, Color color){
        super(posX, posY, color);
    }

    @Override
    public boolean canMove(int desX, int desY) {
        //Can move over own pieces.
        //Can move to enemy piece(Eat) but not over it.
        //Has to move 2+1 (forwards, backwards, left and right)

        return true;
    }
}

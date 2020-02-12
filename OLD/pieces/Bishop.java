package pieces;

public class Bishop extends Piece {

    public Bishop(int posX, int posY, Color color){
        super(posX, posY, color);
    }

    @Override
    public boolean canMove(int desX, int desY) {
        //Can't move over own pieces. So has to stop tile before own piece.
        //Can move to enemy piece(Eat) but not over it.
        //Can move only diagonally (x+n, y+n / x-n, y-n,)

        return true;
    }
}

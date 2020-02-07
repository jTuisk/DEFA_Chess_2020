package pieces;

public class Pawn extends Piece {

    public Pawn(int posX, int posY, Color color){
        super(posX, posY, color);
    }

    @Override
    public boolean canMove(int desX, int desY) {
        //Can't move over own pieces. So has to stop tile before own piece.
        //Can move to enemy piece(Eat) but not over it.
        //Can move up two tiles tile at startPos otherwise can move only one tile at the time.
        //Can only eat diagonally

        return true;
    }
}
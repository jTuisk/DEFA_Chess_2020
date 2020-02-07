import pieces.Piece;

public class Board {


    private Tile[][] board;

    public Board(int height, int width){
        board = new Tile[height][width];
    }

    public Tile getTile(int x, int y){
        return board[x][y];
    }

    private void setupTiles(){

    }

}

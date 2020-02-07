import pieces.Piece;

public class Board {

    /*Board size * tiles */
    private final int   boardSizeHeight,
                        boardSizeWidth;

    private Tile[][] board;

    public Board(){
        this.boardSizeHeight = 8;
        this.boardSizeWidth = 8;
        board = new Tile[this.boardSizeHeight][this.boardSizeWidth];
    }

    public Tile getTile(int x, int y){
        return board[x][y];
    }

    
}

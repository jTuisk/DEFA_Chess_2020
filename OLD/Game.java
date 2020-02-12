

public class Game {

    static BoardJFrame jFrame;
    static Board board;
    

    public static void main(String[] arg0){
        board = new Board(Config.BOARD_TILES_HEIGHT, Config.BOARD_TILES_WIDTH);
        jFrame = new BoardJFrame();
    }


    static void restartGame(){

    }
}

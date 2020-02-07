import java.awt.GraphicsConfiguration;

public class Game {

    static GraphicsConfiguration gc;
    static BoardJFrame jFrame;
    static Board board;
    

    public static void main(String[] arg0){
        jFrame = new BoardJFrame(gc);
        board = new Board();
    }


    static void restartGame(){

    }
}

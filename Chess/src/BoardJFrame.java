import javafx.stage.Screen;

import java.awt.GraphicsConfiguration;
import javax.swing.JFrame;

public class BoardJFrame {

    static GraphicsConfiguration gc;

    String name = "DEFA 2020: Chess game";

    public BoardJFrame(GraphicsConfiguration gc){
        this.gc = gc;
        JFrame frame = new JFrame(gc);
        frame.setTitle(this.name);
        frame.setSize(800,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

}

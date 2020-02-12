import javafx.stage.Screen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardJFrame {

    JPanel gameMenu;
    JPanel leftMenu;


    JButton[][] tileButtons;


    public BoardJFrame(){
        JFrame frame = new JFrame();
        frame.setTitle(Config.APPLICATION_TITLE);
        frame.setBackground(Config.APPLICATION_SCREEN_BACKGROUND_COLOR);
        frame.setSize(Config.APPLICATION_SCREEN_WIDTH,Config.APPLICATION_SCREEN_HEIGHT);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);



        gameMenu = new JPanel();
        gameMenu.setBounds(Config.GAME_SCREEN_START_POS_X, Config.GAME_SCREEN_START_POS_Y, Config.GAME_SCREEN_WIDTH, Config.GAME_SCREEN_HEIGHT);
        gameMenu.setBackground(Config.GAME_SCREEN_BACKGROUND_COLOR);
        gameMenu.setLayout(null);


        tileButtons = new JButton[Config.BOARD_TILES_HEIGHT][Config.BOARD_TILES_WIDTH];

        int buttonSizeWidth = Config.GAME_SCREEN_WIDTH/Config.BOARD_TILES_WIDTH;
        int buttonSizeHeight = Config.GAME_SCREEN_HEIGHT/Config.BOARD_TILES_HEIGHT;
        for(int x = 0; x < tileButtons.length; x++){
            int startPosX = buttonSizeHeight*x;
            for(int y = 0; y < tileButtons[x].length; y++){
                int startPosY = buttonSizeWidth*y;
                tileButtons[x][y] = new JButton();
                tileButtons[x][y].setName(x+""+y);
                tileButtons[x][y].setBounds(startPosX, startPosY, buttonSizeWidth, buttonSizeHeight);
                tileButtons[x][y].setBackground((x+y) % 2 == 0 ? Config.GAME_BOARD_COLOR_DARK : Config.GAME_BOARD_COLOR_LIGHT);
                tileButtons[x][y].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(((JButton) e.getSource()).getName() + " Click");
                    }
                });
                gameMenu.add(tileButtons[x][y]);
            }
        }
        System.out.println(tileButtons.length + " - " + tileButtons[0].length);


        frame.add(gameMenu);


        leftMenu = new JPanel();
        leftMenu.setBounds(Config.STATS_SCREEN_START_POS_X, Config.STATS_SCREEN_START_POS_Y, Config.STATS_SCREEN_WIDTH, Config.STATS_SCREEN_HEIGHT);
        leftMenu.setBackground(Config.STATS_SCREEN_BACKGROUND_COLOR);
        frame.add(leftMenu);


        frame.setVisible(true);

    }

}

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * TankGame Main star
 */
public class TankWarGame {



    public static void main(String[] args) {
        //TankWar Star Game
        JFrame frame = new JFrame();
        GameClient gameClient = new GameClient(1430,825);
        frame.setTitle("TankWar - V1");
        frame.setResizable(false);
        frame.add(gameClient);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameClient.repaint();


        frame.addKeyListener(new KeyAdapter() {
            /**
             * @param e 傳入按壓的按鍵
             */
            @Override
            public void keyPressed(KeyEvent e) {
                gameClient.keyPressed(e);
//                super.keyPressed(e);
//                System.out.println((char)e.getKeyCode());
            }

            /**
             * @param e 傳入放開的按鍵
             */
            @Override
            public void keyReleased(KeyEvent e) {
                gameClient.keyReleased(e);
            }
        });


    }
}

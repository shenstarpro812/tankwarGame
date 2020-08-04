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
        GameClinet gameClinet = new GameClinet(1430,825);
        frame.setTitle("TankWar - V1");
        frame.setResizable(false);
        frame.add(gameClinet);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameClinet.repaint();


        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                gameClinet.keyPressed(e);
//                super.keyPressed(e);
//                System.out.println((char)e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {

                gameClinet.keyReleased(e);
            }
        });


    }
}

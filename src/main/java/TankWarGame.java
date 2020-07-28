import javax.swing.*;
/**
 * TankGame Main star
 */
public class TankWarGame {
    public static void main(String[] args) {
        //TankWar Star Game
        JFrame frame = new JFrame();
        frame.add(new GameClinet(1430,825));
        frame.setTitle("TankWar - V1");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }
}

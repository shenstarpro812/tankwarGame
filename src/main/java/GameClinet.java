import javax.swing.*;
import java.awt.*;

public class GameClinet extends JComponent {
    private int screenWidth;
    private int screenHeight;


    GameClinet(){
        this(800,600);
    }

    GameClinet(int screenWidth,int screenHeight){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(new ImageIcon("assets/images/itankD.png").getImage(),
                715,120,null);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    private int getCenterScreenH(int screenHeight){ return this.screenHeight-screenHeight / 2; }

    private int getCenterScreenW(int screenWidth){ return this.screenWidth-screenWidth / 2; }
}

import javax.swing.*;
import java.awt.*;

public class GameClinet extends JComponent {
    private int screenWidth;
    private int screenHeight;
    private Tank playerTank;

    GameClinet(){
        this(800,600);
    }

    GameClinet(int screenWidth,int screenHeight){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        init();
    }

    //物件初始化
    public void init(){
        playerTank = new Tank(680,115,Direction.DOWN);
    }

    //繪製
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(playerTank.getImage(),
                playerTank.getX(),playerTank.getY(),null);
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

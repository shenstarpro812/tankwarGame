import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameClinet extends JComponent {
    private int screenWidth;
    private int screenHeight;
    private Tank playerTank;

    private boolean stop;

    GameClinet(){
        this(800,600);
    }
    GameClinet(int screenWidth,int screenHeight){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        init();

        new Thread(()->{
            while (!stop){
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    //物件初始化
    public void init(){
        playerTank = new Tank(680,115,Direction.DOWN,5);
    }

    //繪製
    @Override
    protected void paintComponent(Graphics g) {
      playerTank.draw(g);
    }

    /**
     * 控制
     * @param e 發生按下與放開事件
     */
    public void keyPressed(KeyEvent e){
        boolean[] dirs =playerTank.getDirs();
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                dirs[0] = true;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = true;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = true;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = true;
                break;
        }
    }
    public void keyReleased(KeyEvent e) {
        boolean[] dirs =playerTank.getDirs();
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                dirs[0] = false;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = false;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = false;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = false;
                break;
        }
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

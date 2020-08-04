import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameClinet extends JComponent {
    private int screenWidth;
    private int screenHeight;
    private Tank playerTank;
    private List<Tank> enemyTanks = new ArrayList<>();
    private  List<Wall> walls = new ArrayList<>();
    private List<GameObject> objects = new ArrayList<>();

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
        //2020/ 08 /04
        //Player
//        playerTank = new Tank(680,115,Direction.DOWN,5);
//        //enemy
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 4; j++) {
//                enemyTanks.add(new Tank(560+j*80,500+i*80,Direction.UP,5,true));
//            }
//        }
//        Wall[] walls ={
//                new Wall(450,180,true,15,),
//                new Wall(350,250,false,15),
//                new Wall(1000,250,false,15)
//
//        };
    }

    //繪製
    @Override
    protected void paintComponent(Graphics g) {
      playerTank.draw(g);
      //enemy
      for (Tank enemy:enemyTanks){
          enemy.draw(g);
      }
      //Wall
      for (Wall wall : walls){
          wall.draw(g);
      }

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

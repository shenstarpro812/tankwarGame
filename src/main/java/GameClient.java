import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 遊戲客戶端
 * 遊戲控制端
 */
public class GameClient extends JComponent {

    private int screenWidth;
    private int screenHeight;

    private Tank playerTank;
    private List<Tank> enemyTanks = new ArrayList<>();
    private List<GameObject> objects = new ArrayList<>();

    private boolean stop;

    GameClient(){
        this(800,600);
    }
    GameClient(int screenWidth, int screenHeight){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        init();

        new Thread(()->{
            while (!stop){
                //重新繪製
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
        Image[] brickImage = {Tools.getImage("brick.png")};
        Image[] iTankImage = new Image[8];
        Image[] eTankImage = new Image[8];

        String[] subName ={"U.png","D.png","L.png","R.png","LU.png","RU.png","LD.png","RD.png"};

        for (int i = 0; i < iTankImage.length; i++) {
            iTankImage[i] = Tools.getImage("itank"+subName[i]);
            eTankImage[i] = Tools.getImage("etank"+subName[i]);
        }

        //player
        playerTank = new Tank(680,115,Direction.DOWN,5,iTankImage);
        objects.add(playerTank);
        //enemy
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                enemyTanks.add(new Tank(560+j*80,500+i*80,Direction.UP,5,true,eTankImage));
                objects.addAll(enemyTanks);
            }
        }
        //Wall
        Wall[] walls ={
                new Wall(450,180,true,15,brickImage),
                new Wall(350,250,false,15,brickImage),
                new Wall(1000,250,false,15,brickImage)
        };
        objects.addAll(Arrays.asList(walls));

    }

    //繪製
    @Override
    protected void paintComponent(Graphics g) {

        for (GameObject object:objects) {
            object.draw(g);
        }
        
//      playerTank.draw(g);
//      //enemy
//      for (Tank enemy:enemyTanks){
//          enemy.draw(g);
//      }
//      //Wall
//      for (Wall wall : walls){
//          wall.draw(g);
//      }

    }

    /**
     * TankWarGame源
     * @param e 取得被傳入的按鍵
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

    public List<GameObject> getObjects() {
        return objects;
    }

    private int getCenterScreenH(int screenHeight){ return this.screenHeight-screenHeight / 2; }

    private int getCenterScreenW(int screenWidth){ return this.screenWidth-screenWidth / 2; }


}

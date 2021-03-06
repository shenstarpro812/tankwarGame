import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * by Shenstar
 * 遊戲客戶端
 * 遊戲控制端
 */
public class GameClient extends JComponent {
    //子彈Img
    public static Image[] bulletImage = new Image[8];
    public static Image[] explosionImage = new Image[11];
    private int screenWidth;
    private int screenHeight;
    private CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<>();
    private PlayerTank playerTank;
    private boolean stop;
    private boolean gameOver;

    GameClient() {
        this(800, 600);
    }

    GameClient(int screenWidth, int screenHeight) {
        com.sun.javafx.application.PlatformImpl.startup(()->{});
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        init();

        new Thread(() -> {
            while (!stop) {
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
    public void init() {
        gameOver = false;
        objects.clear();
        Image[] brickImage = {Tools.getImage("brick.png")};
        Image[] iTankImage = new Image[8];
        Image[] eTankImage = new Image[8];

        String[] subName = {"U.png", "D.png", "L.png", "R.png", "LU.png", "RU.png", "LD.png", "RD.png"};

        for (int i = 0; i < iTankImage.length; i++) {
            iTankImage[i] = Tools.getImage("itank" + subName[i]);
            eTankImage[i] = Tools.getImage("etank" + subName[i]);
            bulletImage[i] = Tools.getImage("missile" + subName[i]);
        }
        for (int i = 0; i < explosionImage.length; i++) {
            explosionImage[i] = Tools.getImage(i + ".png");
        }

        //player
        playerTank = new PlayerTank(680, 115, Direction.DOWN, 8, iTankImage);
        objects.add(playerTank);
        //enemy
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                //enemyTanks.add(new Tank(560+j*80,500+i*80,Direction.UP,5,true,eTankImage));
                objects.add(new EnemyTank(560 + j * 80, 500 + i * 80, Direction.UP, 5, true, eTankImage));
            }
        }
        //Wall
        Wall[] walls = {
                new Wall(450, 180, true, 15, brickImage),
                new Wall(350, 250, false, 15, brickImage),
                new Wall(1000, 250, false, 15, brickImage),
                new Wall(550, 300, true, 5, brickImage),
                new Wall(850, 300, false, 5, brickImage)
        };
        objects.addAll(Arrays.asList(walls));

    }

    //繪製
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getScreenWidth(), getScreenHeight());
        for (GameObject object : objects) {
            object.draw(g);
        }
        for (GameObject object : objects) {
            if (!object.alive) {
                objects.remove(object);
            }
        }
        checkGameStatus();
        if(gameOver){
            g.setFont(new Font(null, Font.BOLD,100));
            g.setColor(Color.RED);
            g.drawString("GAME OVER",400,100);
            g.setFont(new Font(null, Font.BOLD,50));
            g.setColor(Color.WHITE);
            g.drawString("PRESS    F2     TO      RESTART",350,450);
        }

//        Iterator<GameObject> iterator = objects.iterator();
//        while (iterator.hasNext()){
//            if(!(iterator.next()).alive){
//                iterator.remove();
//            }
//        }
        System.out.println(objects.size());


    }

    /**
     * TankWarGame源
     *
     * @param e 取得被傳入的按鍵
     */
    public void keyPressed(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
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
            case KeyEvent.VK_CONTROL:
                if (!gameOver)
                    playerTank.firing();
                break;
            case KeyEvent.VK_A:
                if (!gameOver)
                    playerTank.superFire();
                break;
            case KeyEvent.VK_F2:
                init();
                break;

        }
    }

    public void keyReleased(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
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

    public void checkGameStatus(){
        if(gameOver){
            return;
        }
        if(!playerTank.alive){
            gameOver=true;
            return;
        }

    }


    public void addGameObject(GameObject object) {
        objects.add(object);
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

    private int getCenterScreenH(int screenHeight) {
        return this.screenHeight - screenHeight / 2;
    }

    private int getCenterScreenW(int screenWidth) {
        return this.screenWidth - screenWidth / 2;
    }


}

import javax.swing.*;
import java.awt.*;

public class Wall {
    private int x;
    private int y;
    //水平與垂直
    private  boolean horizonal;
    private int bricks;

    private Image image;

    public Wall(int x, int y, boolean horizonal, int bricks) {
        this.x = x;
        this.y = y;
        this.horizonal = horizonal;
        this.bricks = bricks;

        image = new ImageIcon("assets/images/brick.png").getImage();
    }

    public void draw(Graphics g){
        if(horizonal){
            for (int i = 0; i < bricks; i++) {
                g.drawImage(image,x+i*image.getWidth(null),y,null );
            }
        }else {
            for (int i = 0; i < bricks; i++) {
                g.drawImage(image,x,y+i*image.getHeight(null),null );
            }
        }

    }




    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isHorizonal() {
        return horizonal;
    }

    public void setHorizonal(boolean horizonal) {
        this.horizonal = horizonal;
    }

    public int getBricks() {
        return bricks;
    }

    public void setBricks(int bricks) {
        this.bricks = bricks;
    }
}

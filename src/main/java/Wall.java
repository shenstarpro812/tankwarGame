import javax.swing.*;
import java.awt.*;

/**
 * 牆壁物件　（Wall Object）
 */
public class Wall extends GameObject{
    //水平與垂直
    private  boolean horizonal;
    //牆壁產生數量
    private int bricks;


    public Wall(int x, int y, boolean horizonal, int bricks,Image[] image) {
        super(x,y,image);
        this.horizonal = horizonal;
        this.bricks = bricks;

    }

    @Override
    public Rectangle getRectangle() {
        return horizonal?new Rectangle(x,y,bricks*width,height) :
                new Rectangle(x,y,width,bricks*height);
    }

    public void draw(Graphics g){
        if(horizonal){
            for (int i = 0; i < bricks; i++) {
                g.drawImage(image[0],x+i*width ,y,null );
            }
        }else {
            for (int i = 0; i < bricks; i++) {
                g.drawImage(image[0],x,y+i*height,null );
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

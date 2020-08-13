import org.w3c.dom.css.Rect;

import java.awt.*;

/**
 * 遊戲物件類別
 * 共同與相同屬性、方法
 */
public abstract class GameObject {
    protected int oldx;
    protected int oldy;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean alive;
    protected int frame;

    protected Image[] image;

    public GameObject(int x, int y, Image[] image) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width =image[0].getWidth(null);
        this.height = image[0].getHeight(null);
        this.alive = true;
    }

    public Rectangle getRectangle(){
        return new Rectangle(x,y,width,height);
    }

    /**
     * 繪製
     * @param g　繼承必須實作方法
     */
    abstract void draw(Graphics g);
}

import javax.swing.*;
import java.awt.*;

/**
 * Tank Object
 */
public class Tank extends GameObject {
    private int x;
    private int y;
    private double speed;
    private Direction direction;
    private boolean enemy;
    private boolean[] dirs = new boolean[4];

    public Tank(int x, int y, Direction direction,double speed,Image image) {
        //Call 1
        this(x,y,direction,speed,false,image);
    }
    //1
    public Tank(int x, int y, Direction direction,double speed,boolean enemy,Image image) {
        super(x,y,image);   
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
        this.enemy = enemy;
    }

    public void move(){
        switch (direction){
            case UP:
                y-=speed;
                break;
            case DOWN:
                y+=speed;
                break;
            case LEFT:
                x-=speed;
                break;
            case RIGHT:
                x+=speed;
                break;
            case UP_LEFT:
                y-=speed;
                x-=speed;
                break;
            case UP_RIGHT:
                y-=speed;
                x+=speed;
                break;
            case DOWN_LEFT:
                y+=speed;
                x-=speed;
                break;
            case DOWN_RIGHT:
                y+=speed;
                x+=speed;
                break;

        }
    }

    //取得方向
    public Image getImage(){
        //分類敵人與玩家物件
        String p_Name = enemy? "etank":"itank";
        
        if(direction==Direction.UP)
            return new ImageIcon("assets/images/"+p_Name+"U.png").getImage();
        if(direction==Direction.DOWN)
            return new ImageIcon("assets/images/"+p_Name+"D.png").getImage();
        if(direction==Direction.LEFT)
            return new ImageIcon("assets/images/"+p_Name+"L.png").getImage();
        if(direction==Direction.RIGHT)
            return new ImageIcon("assets/images/"+p_Name+"R.png").getImage();
        if(direction==Direction.UP_LEFT)
            return new ImageIcon("assets/images/"+p_Name+"LU.png").getImage();
        if(direction==Direction.UP_RIGHT)
            return new ImageIcon("assets/images/"+p_Name+"RU.png").getImage();
        if(direction==Direction.DOWN_LEFT)
            return new ImageIcon("assets/images/"+p_Name+"LD.png").getImage();
        if(direction==Direction.DOWN_RIGHT)
            return new ImageIcon("assets/images/"+p_Name+"RD.png").getImage();
        return null;
    }
    private void determineDirection(){
        // 1:上 , 2:下 , 3:左 , 4:右
        if(dirs[0] && !dirs[1] && !dirs[2] && !dirs[3]) { direction = Direction.UP; }//上
        else if (dirs[0] && !dirs[1] && dirs[2] && !dirs[3]){ direction = Direction.UP_LEFT; }//上左
        else if (dirs[0] && !dirs[1] && !dirs[2] && dirs[3]){ direction = Direction.UP_RIGHT; }//上右
        else if (!dirs[0] && dirs[1] && !dirs[2] && !dirs[3]){ direction = Direction.DOWN; }//下
        else if (!dirs[0] && dirs[1] && dirs[2] && !dirs[3]){ direction = Direction.DOWN_LEFT; }//下左
        else if (!dirs[0] && dirs[1] && !dirs[2] && dirs[3]){ direction = Direction.DOWN_RIGHT; }//下右
        else if (!dirs[0] && !dirs[1] && dirs[2] && !dirs[3]){ direction = Direction.LEFT; }//左
        else if (!dirs[0] && !dirs[1] && !dirs[2] && dirs[3]){ direction = Direction.RIGHT; }//右
    }
    public boolean isStop(){
        for (boolean dir : dirs){
            if(dir){
                return false;
            }
        }
        return true;
    }

    public void draw(Graphics g) {
        if(!isStop()) {
            determineDirection();
            move();
        }
        g.drawImage(getImage(),x ,y,null);
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean[] getDirs() {
        return dirs;
    }

    public void setDirs(boolean[] dirs) {
        this.dirs = dirs;
    }


}

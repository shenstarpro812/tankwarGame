import java.awt.*;

public abstract class MoveObject extends GameObject{
    private double speed;
    protected Direction direction;
    protected boolean enemy;

    public MoveObject(int x, int y, Image[] image) {
        super(x, y,image);
    }
    public MoveObject(int x, int y, Direction direction,double speed,boolean enemy,Image[] image) {
        super(x,y,image);
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
        this.enemy = enemy;
    }


    public void move(){
        oldx = x;
        oldy = y;
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

    public boolean collisionBound() {
        boolean collisionStaut = false;
        if (x < 0) {
            x = 0;
            collisionStaut = true;
        } else if (x > TankWarGame.gameClient.getScreenWidth() - width) {
            x = TankWarGame.gameClient.getScreenWidth() - width;
            collisionStaut = true;
        }
        if (y < 0) {
            y = 0;
            collisionStaut = true;
        } else if (y > TankWarGame.gameClient.getScreenHeight() - height) {
            y = TankWarGame.gameClient.getScreenHeight() - height;
            collisionStaut = true;
        }
        return collisionStaut;
    }

    public abstract boolean collision();


    public void draw(Graphics g) {
        move();
        collision();
        g.drawImage(image[direction.ordinal()],x ,y,null);
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

}

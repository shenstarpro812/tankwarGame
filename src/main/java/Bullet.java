import java.awt.*;

public class Bullet extends MoveObject {

    public Bullet(int x, int y, Direction direction, double speed, boolean enemy, Image[] image) {
        //default Speed 10
        super(x, y, direction, speed, enemy, image);
    }

    @Override
    public void draw(Graphics g) {
        if (!alive) {
            return;
        }
        move();
        collision();
        g.drawImage(image[direction.ordinal()], x, y, null);
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

    @Override
    public void collision() {
        if (collisionBound()) {
            alive = false;
            return;
        }

        //物件偵測
        for (GameObject object : TankWarGame.gameClient.getObjects()) {
            if (object == this) {
                continue;
            }
            //偵測坦克
            if (object instanceof Tank) {
                if (((Tank) object).enemy == enemy) {
                    continue;
                }
            }
            //碰撞物體個體消失
            if (getRectangle().intersects(object.getRectangle())) {
                alive = false;
                if(object instanceof Tank){
                    object.alive = false;
                }
                return;
            }
        }
    }
}

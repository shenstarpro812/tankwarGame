import java.awt.*;

public class PlayerTank extends Tank implements SuperFire{

    public PlayerTank(int x, int y, Direction direction, double speed, Image[] image) {
        super(x, y, direction, speed, image);
    }
    @Override
    public void superFire() {
        for(Direction direction:Direction.values()){
            Bullet bullet = new Bullet(((x+width/2)-(GameClient.bulletImage[0].getWidth(null)/2)),
                    (y+height/2)-(GameClient.bulletImage[0].getHeight(null)/2),direction,15,enemy,GameClient.bulletImage);
            bullet.setSpeed(20);
            TankWarGame.gameClient.addGameObject(bullet);
        }
    }
}

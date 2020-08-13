import java.awt.*;
import java.util.Random;

public class EnemyTank extends Tank{
    public EnemyTank(int x, int y, Direction direction, double speed, boolean enemy, Image[] image) {
        super(x, y, direction, speed, enemy, image);
    }

    public void AI(){
        Random random = new Random();
        //  20/1 移動
        if(random.nextInt(20)==1){
            dirs = new boolean[4];
            // 2/1 停止移動
            if(random.nextBoolean()==true){
                return;
            }
            getNewDirection();
        }
        //  50/1 開火
        if(random.nextInt(50)==1){
            firing();
        }
    }

    @Override
    public void draw(Graphics g) {
        AI();
        super.draw(g);
    }

    @Override
    public boolean collision() {
        if(super.collision()){
            getNewDirection();
            return true;
        }
        return false;
    }


    public void getNewDirection(){
        Random random = new Random();
        //取得方向長度
        int dir = random.nextInt(Direction.values().length);
        if(dir <= Direction.RIGHT.ordinal()){
            dirs[dir] = true;
        }else {
            // 0:上 , 1:下 , 2:左 , 3:右   Length:4
            if(dir == Direction.UP_LEFT.ordinal()){
                dirs[0] =true;
                dirs[2]=true;
            }else if(dir==Direction.UP_RIGHT.ordinal()){
                dirs[0] =true;
                dirs[3]=true;
            }else if(dir==Direction.DOWN_LEFT.ordinal()){
                dirs[1] =true;
                dirs[2]=true;
            }else if(dir==Direction.DOWN_RIGHT.ordinal()){
                dirs[1] =true;
                dirs[3]=true;
            }
        }
    }
}

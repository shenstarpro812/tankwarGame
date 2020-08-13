import javax.swing.*;
import java.awt.*;

/**
 * 坦克物件 (Tank Object)
 */
public class Tank extends MoveObject {

    protected boolean enemy;
    protected boolean[] dirs = new boolean[4];

    public Tank(int x, int y, Direction direction,double speed,Image[] image) {
        //Call 1
        this(x,y,direction,speed,false,image);
    }
    //1
    public Tank(int x, int y, Direction direction,double speed,boolean enemy,Image[] image) {
        super(x,y,direction,speed,enemy,image);
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.enemy = enemy;
    }

    public boolean collision(){
        //邊界檢測
        if (x<0){
            x=0;
            return true;
        }else if(x>TankWarGame.gameClient.getScreenWidth()-width){
            x=TankWarGame.gameClient.getScreenWidth()-width;
            return true;
        }
        if(y<0){
            y=0;
            return true;
        }else if(y>TankWarGame.gameClient.getScreenHeight()-height){
            y=TankWarGame.gameClient.getScreenHeight()-height;
            return true;
        }
        //物件偵測
        for(GameObject object:TankWarGame.gameClient.getObjects()){
            if(object!=this){
                if(getRectangle().intersects(object.getRectangle())){
//                    if(((Wall)object).isHorizonal()){
//                        x = oldx;
//                        y = --oldy;
//                    }else {
//                        x= --oldx;
//                        y = oldy;
//                    }
                    x = oldx;
                    y = oldy;
                    return true;
                }
            }
        }
        return false;
    }

    //取得方向
    public Image getImage(){
        //分類敵人與玩家物件
//        String p_Name = enemy? "etank":"itank";
        //依序給予方向
        return image[direction.ordinal()];
//        if(direction==Direction.UP)
//            return new ImageIcon("assets/images/"+p_Name+"U.png").getImage();
//        if(direction==Direction.DOWN)
//            return new ImageIcon("assets/images/"+p_Name+"D.png").getImage();
//        if(direction==Direction.LEFT)
//            return new ImageIcon("assets/images/"+p_Name+"L.png").getImage();
//        if(direction==Direction.RIGHT)
//            return new ImageIcon("assets/images/"+p_Name+"R.png").getImage();
//        if(direction==Direction.UP_LEFT)
//            return new ImageIcon("assets/images/"+p_Name+"LU.png").getImage();
//        if(direction==Direction.UP_RIGHT)
//            return new ImageIcon("assets/images/"+p_Name+"RU.png").getImage();
//        if(direction==Direction.DOWN_LEFT)
//            return new ImageIcon("assets/images/"+p_Name+"LD.png").getImage();
//        if(direction==Direction.DOWN_RIGHT)
//            return new ImageIcon("assets/images/"+p_Name+"RD.png").getImage();
//        return null;
    }
    private void determineDirection(){
        // 0:上 , 1:下 , 2:左 , 3:右
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
            collision();
        }
        g.drawImage(image[direction.ordinal()],x ,y,null);
    }

    //Features
    public void firing(){
        Bullet bullet = new Bullet(((x+width/2)-(GameClient.bulletImage[0].getWidth(null)/2)),
                (y+height/2)-(GameClient.bulletImage[0].getHeight(null)/2),direction,15,enemy,GameClient.bulletImage);
        TankWarGame.gameClient.addGameObject(bullet);
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


    public boolean[] getDirs() {
        return dirs;
    }

    public void setDirs(boolean[] dirs) {
        this.dirs = dirs;
    }


}

import java.awt.*;
/**
 * 爆炸類別
 * 用於動畫效果
 */
public class Explosion extends GameObject{

    public Explosion(int x, int y, Image[] image) {
        super(x, y, image);
        Tools.playAudio("explode.wav");
        new Thread(()->{
            while (alive){
                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(++frame >= image.length){
                    alive = false;
                }
            }

        }).start();
    }

    @Override
    void draw(Graphics g) {
        if(alive){
            g.drawImage(image[frame],x,y,null);
        }
    }
}

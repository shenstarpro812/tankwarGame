import javax.swing.*;
import java.awt.*;


/**
 * 工具類別
 * 取用共同方法與方式
 */
public class Tools {
    /**
     * 取得圖片
     * @param fileName 檔案名稱
     * @return 回傳完整共同路徑
     */
    public static Image getImage(String fileName){
        return new ImageIcon("assets/images/"+fileName).getImage();
    }
}

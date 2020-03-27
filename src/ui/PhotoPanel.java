package ui;

import tools.FileTools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author ypl
 * @date 2020/1/9
 */
public class PhotoPanel extends JPanel {

    private BufferedImage image = null;

    /**
     * 构造函数
     *
     * @param width 宽
     * @param height 高
     */
    public PhotoPanel(int width, int height) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.setBorder(BorderFactory.createLoweredSoftBevelBorder());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        try {
            File file = new File(FileTools.getLastPhotoPath());
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 图片为320*240 此处把图片放大两倍
        g.drawImage(image, 2, 2, 640, 480, this);
    }

}

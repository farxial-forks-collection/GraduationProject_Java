package ui;

import tools.FileTools;
import tools.IoTools;
import tools.OtherTools;

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

    private byte[] bytes = null;

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
        BufferedImage image = null;
        File file;
        try {
            if (bytes == null) {
                file = FileTools.getLastPicture();
            } else {
                file = FileTools.getNewPicture();
                // 长度为2, 存储point_ffd8和point_ffd9
                int[] position = OtherTools.getFFD8AndFFD9(bytes);
                // 生成图片
                IoTools.writeIntoFile(file, bytes, position[0], position[1] - position[0] + 1, false);
                TcpUi.printMessage("图片已保存: " + file.getAbsolutePath());
            }
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 图片为320*240 此处把图片放大两倍
        g.drawImage(image, 2, 2, 640, 480, this);
    }


    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
        repaint();
    }

}

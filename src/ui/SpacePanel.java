package ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author ypl
 * @date 2020/1/9
 */
public class SpacePanel extends JPanel {

    /**
     * 构造函数
     *
     * @param width
     * @param height
     * @param color
     */
    public SpacePanel(int width, int height, Color color) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(color);
    }

}

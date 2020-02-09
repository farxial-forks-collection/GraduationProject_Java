package ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author ypl
 * @date 2020/1/10
 */
public class RightPanel extends JPanel {

    public RightPanel(int width, int height, TcpUI tcpUI) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new FlowLayout());
        this.add(new ControlPanel(width, 260, tcpUI));
        this.add(new ConvertPanel(width, 220));
    }

}

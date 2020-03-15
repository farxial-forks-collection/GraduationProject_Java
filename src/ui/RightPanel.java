package ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author ypl
 * @date 2020/1/10
 */
public class RightPanel extends JPanel {

    public RightPanel(int width, int height, TcpUi tcpUI) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new FlowLayout());

        // 选项卡
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        // 添加两个新的选项卡
        tabbedPane.addTab("Control", new ControlPanel(width, height, tcpUI));
        tabbedPane.addTab("Convert", new ConvertPanel(width, height));
        this.add(tabbedPane);
    }

}

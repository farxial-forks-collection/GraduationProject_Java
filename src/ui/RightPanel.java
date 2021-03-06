package ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author ypl
 * @date 2020/1/10
 */
public class RightPanel extends JPanel {

    public RightPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout());

        // 选项卡
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        // 添加两个新的选项卡
        tabbedPane.addTab("控制", new ControlPanel(width, height));
        tabbedPane.addTab("调试", new ConvertPanel(width, height));
        this.add(tabbedPane);
    }

}

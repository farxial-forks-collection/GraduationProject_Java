package component;

import javax.swing.*;
import java.awt.*;

/**
 * @author ypl
 * @date 2020/1/9
 */
public class MyButton extends JButton {

    /**
     * 构造函数
     *
     * @param width 按钮的宽
     * @param height 按钮的高
     * @param text 按钮所显示的文字
     * @param actionCommand 事件监听机制的命令
     */
    public MyButton(int width, int height, String text, String actionCommand) {
        super(text);
        // 设置大小
        this.setPreferredSize(new Dimension(width, height));
        // 设置命令
        this.setActionCommand(actionCommand);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
    }

}

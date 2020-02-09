package component;

import javax.swing.*;

/**
 * 自定义复选框
 *
 * @author ypl
 * @date 2020/1/10
 */
public class MyRadioButton extends JRadioButton {

    /**
     * 构造函数
     *
     * @param text 文本提示信息
     * @param isOpaque 是否不透明
     * @param actionCommand 事件监听机制的命令
     * @param isSelected 是否被选中
     */
    public MyRadioButton(String text, boolean isOpaque, String actionCommand, boolean isSelected) {
        super(text);
        // 设置是否不透明
        this.setOpaque(isOpaque);
        // 设置命令
        this.setActionCommand(actionCommand);
        // 是否被选中
        this.setSelected(isSelected);
    }

}

package ui;

import component.MyTextArea;
import main.Constant;
import tools.OsTools;

import javax.swing.*;
import java.awt.*;

/**
 * @author ypl
 * @date 2020/1/9
 */
public class MessagePanel extends JScrollPane implements Constant {

    private JTextArea textArea;
    private JScrollBar scrollBar;

    /**
     * 构造函数
     *
     * @param width 宽
     * @param height 高
     * @param textArea 文本域
     */
    public MessagePanel(int width, int height, MyTextArea textArea) {
        super(textArea);
        this.textArea = textArea;
        textArea.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(width, height));
        // 永远不显示水平滚动条
        this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        // 需要时才显示垂直滚动条
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollBar = this.getVerticalScrollBar();
    }

    // 以下为外部可调用方法 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * 在信息提示栏添加新的消息
     *
     * @param message 要添加的消息
     */
    public void append(String message) {
        textArea.append(message + OsTools.getLineSeparator());
        // 信息自动滚动到最底端
        scrollBar.setValue(scrollBar.getMaximum());
    }

    /**
     * 清空信息提示栏的消息
     */
    public void clear() {
        textArea.setText("");
    }

}

package ui;

import main.Constant;
import component.MyTextArea;
import tools.OsTools;

import javax.swing.*;
import java.awt.*;

/**
 * @author ypl
 * @date 2020/1/9
 */
public class MessagePanel extends JScrollPane implements Constant {

    private JScrollBar scrollBar;
    private JTextArea textArea;

    /**
     * 构造函数
     *
     * @param width
     * @param height
     * @param textArea
     */
    public MessagePanel(int width, int height, MyTextArea textArea) {
        super(textArea);
        this.textArea = textArea;
        textArea.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(width, height));
        this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollBar = this.getVerticalScrollBar();
    }

    // 以下为外部可调用方法 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public void append(String message) {
        textArea.append(message + OsTools.getLineSeparator());
        // 信息自动滚动到最底端
        scrollBar.setValue(scrollBar.getMaximum());
    }

    public void clear() {
        textArea.setText("");
    }

}

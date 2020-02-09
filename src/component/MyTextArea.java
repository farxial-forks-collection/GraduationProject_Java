package component;

import javax.swing.*;
import java.awt.*;

/**
 * 自定义文本域
 *
 * @author ypl
 * @date 2020/1/9
 */
public class MyTextArea extends JTextArea {

    /**
     * 构造函数
     */
    public MyTextArea() {
        super();
        // 设置背景
        this.setBackground(Color.WHITE);
        // 设置为不可编辑文字(不能修改文字)
        this.setEditable(false);
        // 文字过长, 不自动换行
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
    }

}

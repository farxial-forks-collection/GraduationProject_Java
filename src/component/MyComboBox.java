package component;

import javax.swing.*;
import java.awt.*;

/**
 * 自定义下拉框
 *
 * @author ypl
 * @date 2020/1/9
 */
public class MyComboBox extends JComboBox {

    /**
     * 构造函数
     *
     * @param width 宽
     * @param height 高
     * @param ints 组合框的数据
     * @param selectedIndex 初始所选的位置
     * @param actionCommand 事件监听机制的命令
     */
    public MyComboBox(int width, int height, Integer[] ints, int selectedIndex, String actionCommand) {
        super(ints);
        // 设置初始被选择的位置
        this.setSelectedIndex(selectedIndex);
        // 设置命令
        this.setActionCommand(actionCommand);
        // 设置大小
        this.setPreferredSize(new Dimension(width, height));
    }

    /**
     * 构造函数
     *
     * @param width 宽
     * @param height 高
     * @param count 所包含数据的数量(从0开始)
     * @param selectedIndex 初始所选的位置
     * @param actionCommand 事件监听机制的命令
     */
    public MyComboBox(int width, int height, int count, int selectedIndex, String actionCommand) {
        this(width, height, getInts(count), selectedIndex, actionCommand);
    }

    private static Integer[] getInts(int length) {
        Integer[] ints = new Integer[length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i;
        }
        return ints;
    }

}

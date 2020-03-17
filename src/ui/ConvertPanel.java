package ui;

import component.MyButton;
import convertphoto.ConvertInterface;
import listener.ConvertListener;
import main.Constant;
import component.MyComboBox;
import component.MyRadioButton;
import tools.OsTools;

import javax.swing.*;
import java.awt.*;

/**
 * 转换图片格式面板
 *
 * @author ypl
 * @date 2019/12/28
 */
public class ConvertPanel extends JPanel implements Constant, ConvertInterface {

    private JRadioButton toTxtButton, toJpgButton;
    private JRadioButton linuxButton, winButton;
    private JCheckBox commaCheckBox;
    private MyComboBox lineMaxLengthComboBox;

    /**
     * 构造函数
     *
     * @param width 宽
     * @param height 高
     */
    public ConvertPanel(int width, int height) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout());

        // 选择转换方式面板
        JPanel convertMethodPanel = initConvertMethodPanel(width);
        // 选择操作系统面板
        JPanel osPanel = initOsPanel(width);
        // 行样式面板
        JPanel lineStylePanel = initLineStylePanel(width);
        // 文件选择按钮
        JButton fileChooseButton = new MyButton(140, 30, "Choose File", CHOOSE_FILE_ACTION);

        // 设置监听器
        ConvertListener convertListener = new ConvertListener(this);
        toTxtButton.addActionListener(convertListener);
        toJpgButton.addActionListener(convertListener);
        linuxButton.addActionListener(convertListener);
        winButton.addActionListener(convertListener);
        commaCheckBox.addActionListener(convertListener);
        lineMaxLengthComboBox.addActionListener(convertListener);
        fileChooseButton.addActionListener(convertListener);

        // 添加所有的组件
        this.add(convertMethodPanel);
        this.add(osPanel);
        this.add(lineStylePanel);
        this.add(fileChooseButton);
    }

    // 以下为内部调用方法, 用于添加组件 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * 初始化选择转换方式的面板
     *
     * @param width 宽
     * @return 初始化后的面板
     */
    private JPanel initConvertMethodPanel(int width) {
        // 转换方式单选按钮
        toTxtButton = new MyRadioButton("jpg-txt", false, TO_TXT_ACTION, true);
        toJpgButton = new MyRadioButton("txt-jpg", false, TO_JPG_ACTION, false);
        ButtonGroup methodButtonGroup = new ButtonGroup();
        methodButtonGroup.add(toTxtButton);
        methodButtonGroup.add(toJpgButton);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, 60));
        panel.setBorder(BorderFactory.createTitledBorder("Convert Method"));
        panel.add(toTxtButton);
        panel.add(toJpgButton);
        return panel;
    }

    /**
     * 初始化选择操作系统的面板
     *
     * @param width 宽
     * @return 初始化后的面板
     */
    private JPanel initOsPanel(int width) {
        // 操作系统单选按钮
        winButton = new MyRadioButton("Windows", false, WINDOWS_ACTION, false);
        linuxButton = new MyRadioButton("Linux", false, LINUX_ACTION, false);
        ButtonGroup osButtonGroup = new ButtonGroup();
        osButtonGroup.add(winButton);
        osButtonGroup.add(linuxButton);
        // 自动检测操作系统
        if (OsTools.getOS() == LINUX) {
            linuxButton.setSelected(true);
        } else {
            winButton.setSelected(true);
        }

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, 60));
        panel.setBorder(BorderFactory.createTitledBorder("Select OS"));
        panel.add(winButton);
        panel.add(linuxButton);
        return panel;
    }

    /**
     * <p>初始化行样式的面板</p>
     * <p>样式包括: 每行的字节数, 需不需要逗号</p>
     *
     * @param width 宽
     * @return 初始化后的面板
     */
    private JPanel initLineStylePanel(int width) {
        // 0x与逗号
        commaCheckBox = new JCheckBox("Need 0x & Comma ?");
        commaCheckBox.setOpaque(false);
        commaCheckBox.setSelected(false);
        commaCheckBox.setBorder(BorderFactory.createLoweredBevelBorder());
        commaCheckBox.setActionCommand(COMMA_ACTION);
        // 每行最大数据
        Integer[] ints = new Integer[99];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i + 1;
        }
        lineMaxLengthComboBox = new MyComboBox(60, 20, ints, 31, MAX_LINE_ACTION);
        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createTitledBorder("Line Style"));
        panel.setPreferredSize(new Dimension(width, 100));
        panel.add(commaCheckBox);
        panel.add(new JLabel("Line Count: "));
        panel.add(lineMaxLengthComboBox);
        return panel;
    }

    // 以下为外部调用的方法 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * 将所有组件设置为可以点击
     */
    @Override
    public void setEnable() {
        linuxButton.setEnabled(true);
        winButton.setEnabled(true);
        commaCheckBox.setEnabled(true);
        lineMaxLengthComboBox.setEnabled(true);
    }

    /**
     * 将所有组件设置为不可点击
     */
    @Override
    public void setUnable() {
        linuxButton.setEnabled(false);
        winButton.setEnabled(false);
        commaCheckBox.setEnabled(false);
        lineMaxLengthComboBox.setEnabled(false);
    }

    /**
     * <p>获取当前ComboBox的值</p>
     * 即每行最大字节数
     *
     * @return 当前每行最大的字节数
     */
    @Override
    public int getMaxLineCount() {
        return lineMaxLengthComboBox.getSelectedIndex();
    }

}

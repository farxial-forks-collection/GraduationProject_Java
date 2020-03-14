package ui;

import component.MyButton;
import convertphoto.ConvertInterface;
import listener.ConvertListener;
import main.Constant;
import component.MyComboBox;
import component.MyRadioButton;
import tools.OSTools;

import javax.swing.*;
import java.awt.*;

/**
 * @author ypl
 * @date 2019/12/28
 */
public class ConvertPanel extends JPanel implements Constant, ConvertInterface {

    private JRadioButton linuxButton, winButton;
    private JCheckBox commaCheckBox;
    private JComboBox<Integer> comboBox;

    /**
     * 构造函数
     *
     * @param width
     * @param height
     */
    public ConvertPanel(int width, int height) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout());

        // 转换方式单选按钮
        JRadioButton toTxtButton = new MyRadioButton("jpg-txt", false, TO_TXT_ACTION, true);
        JRadioButton toJpgButton = new MyRadioButton("txt-jpg", false, TO_JPG_ACTION, false);
        ButtonGroup methodButtonGroup = new ButtonGroup();
        methodButtonGroup.add(toTxtButton);
        methodButtonGroup.add(toJpgButton);

        // 操作系统单选按钮
        winButton = new MyRadioButton("Windows", false, WINDOWS_ACTION, false);
        linuxButton = new MyRadioButton("Linux", false, LINUX_ACTION, false);
        ButtonGroup osButtonGroup = new ButtonGroup();
        osButtonGroup.add(winButton);
        osButtonGroup.add(linuxButton);
        // 自动检测操作系统
        if (OSTools.getOS() == LINUX) {
            linuxButton.setSelected(true);
        } else {
            winButton.setSelected(true);
        }

        // 0x与逗号
        commaCheckBox = new JCheckBox("Need 0x & Comma ?");
        commaCheckBox.setOpaque(false);
        commaCheckBox.setSelected(false);
        commaCheckBox.setActionCommand(COMMA_ACTION);

        // 每行最大数据
        Integer[] ints = new Integer[99];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i + 1;
        }
        comboBox = new MyComboBox(60, 20, ints, 31, MAX_LINE_ACTION);

        // 文件选择按钮
        JButton fileChooseButton = new MyButton(140, 20 , "Choose File", CHOOSE_FILE_ACTION);

        // 设置监听器
        ConvertListener convertListener = new ConvertListener(this);
        toTxtButton.addActionListener(convertListener);
        toJpgButton.addActionListener(convertListener);
        linuxButton.addActionListener(convertListener);
        winButton.addActionListener(convertListener);
        commaCheckBox.addActionListener(convertListener);
        comboBox.addActionListener(convertListener);
        fileChooseButton.addActionListener(convertListener);

        // 添加所有的组件
        this.add(new JLabel("CONVERT PHOTO FORMAT"));
        this.add(new JLabel("Select Method: "));
        this.add(toTxtButton);
        this.add(toJpgButton);
        this.add(new JLabel("Select Your OS: "));
        this.add(winButton);
        this.add(linuxButton);
        this.add(commaCheckBox);
        this.add(new JLabel("Line Count: "));
        this.add(comboBox);
        this.add(fileChooseButton);
    }

    @Override
    public void setEnable() {
        linuxButton.setEnabled(true);
        winButton.setEnabled(true);
        commaCheckBox.setEnabled(true);
        comboBox.setEnabled(true);
    }

    @Override
    public void setUnable() {
        linuxButton.setEnabled(false);
        winButton.setEnabled(false);
        commaCheckBox.setEnabled(false);
        comboBox.setEnabled(false);
    }

    @Override
    public int getMaxLineCount() {
        return comboBox.getSelectedIndex();
    }

}

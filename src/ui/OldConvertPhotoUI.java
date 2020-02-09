package ui;

import convertphoto.ConvertInterface;
import main.Constant;
import listener.ConvertListener;
import tools.OS;

import javax.swing.*;
import java.awt.*;

/**
 * <p>此类为 转换图片格式 的UI类, 由主面板+小面板+小面板的组件组成. 小面板宽与主面板一致</p>
 *
 * @author ypl
 * @date 2019/12/27
 */
public class OldConvertPhotoUI implements Constant, ConvertInterface {

    private final int WIDTH = 400;
    private final int HEIGHT = 230;

    JRadioButton linuxButton, winButton;
    JCheckBox commaCheckBox;
    JComboBox<Integer> comboBox;

    public void initUI() {
        // 设置UI的风格为系统默认风格(跨平台的太丑了)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // 1.面板 顶级容器
        JFrame frame = new JFrame();
        frame.setTitle("ConvertPhoto");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());
        Container container = frame.getContentPane();

        // 2.组件 转换方式单选按钮
        ButtonGroup methodButtonGroup = new ButtonGroup();
        JRadioButton toTxtButton = new JRadioButton("jpg->txt");
        JRadioButton toJpgButton = new JRadioButton("txt->jpg");
        toTxtButton.setOpaque(true);
        toJpgButton.setOpaque(true);
        methodButtonGroup.add(toTxtButton);
        methodButtonGroup.add(toJpgButton);
        toTxtButton.setSelected(true);
        toJpgButton.setSelected(false);
        toTxtButton.setActionCommand(TO_TXT_ACTION);
        toJpgButton.setActionCommand(TO_JPG_ACTION);
        // 2.面板 转换方式面板
        JPanel methodPanel = new JPanel();
        methodPanel.setPreferredSize(new Dimension(WIDTH, 30));
        methodPanel.add(new JLabel("Select Method: "));
        methodPanel.add(toTxtButton);
        methodPanel.add(toJpgButton);

        // 2.组件 操作系统单选按钮
        ButtonGroup osButtonGroup = new ButtonGroup();
        linuxButton = new JRadioButton("Linux");
        winButton = new JRadioButton("Windows");
        linuxButton.setOpaque(true);
        winButton.setOpaque(true);
        osButtonGroup.add(linuxButton);
        osButtonGroup.add(winButton);
        linuxButton.setActionCommand(LINUX_ACTION);
        winButton.setActionCommand(WINDOWS_ACTION);
        // 自动检测操作系统
        if (OS.getOS() == LINUX) {
            linuxButton.setSelected(true);
            winButton.setSelected(false);
        } else {
            linuxButton.setSelected(false);
            winButton.setSelected(true);
        }
        // 2.面板 操作系统面板
        JPanel OSPanel = new JPanel();
        OSPanel.setPreferredSize(new Dimension(WIDTH, 30));
        OSPanel.add(new JLabel("Select Your OS: "));
        OSPanel.add(linuxButton);
        OSPanel.add(winButton);

        // 2.组件 0x与逗号
        commaCheckBox = new JCheckBox("在数据前添加\"0x\"与逗号");
        commaCheckBox.setOpaque(true);
        commaCheckBox.setSelected(false);
        commaCheckBox.setActionCommand(COMMA_ACTION);
        // 2.面板 逗号面板
        JPanel commaPanel = new JPanel();
        commaPanel.setPreferredSize(new Dimension(WIDTH, 30));
        commaPanel.add(commaCheckBox);

        // 2.组件 每行最大数据
        Integer[] ints = new Integer[99];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i + 1;
        }
        comboBox = new JComboBox<>(ints);
        comboBox.setSelectedIndex(31);
        comboBox.setActionCommand(MAX_LINE_ACTION);
        // 2.面板 每行最大数据面板
        JPanel linePanel = new JPanel();
        linePanel.setPreferredSize(new Dimension(WIDTH, 30));
        linePanel.add(new JLabel("每行最大数据量 "));
        linePanel.add(comboBox);

        // 2.组件 文件选择按钮
        JButton fileChooseButton = new JButton("Choose File");
        fileChooseButton.setActionCommand(CHOOSE_FILE_ACTION);
        // 2.面板 文件选择面板
        JPanel filePanel = new JPanel();
        filePanel.setPreferredSize(new Dimension(WIDTH, 80));
        filePanel.add(fileChooseButton);

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
        container.add(methodPanel);
        container.add(OSPanel);
        container.add(commaPanel);
        container.add(linePanel);
        container.add(filePanel);
        frame.setVisible(true);
    }

    // 以下方法为外部调用的方法, 是一些操作UI的函数, 且实现了ConvertInterface接口中的抽象方法 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * 将所有组件设置为可以点击
     */
    @Override
    public void setEnable() {
        linuxButton.setEnabled(true);
        winButton.setEnabled(true);
        commaCheckBox.setEnabled(true);
        comboBox.setEnabled(true);
    }

    /**
     * 将所有组件设置为不可点击
     */
    @Override
    public void setUnable() {
        linuxButton.setEnabled(false);
        winButton.setEnabled(false);
        commaCheckBox.setEnabled(false);
        comboBox.setEnabled(false);
    }

    /**
     * 得到当前所选择的每行最大数据量
     *
     * @return 当前选择的每行最大数据量
     */
    @Override
    public int getMaxLineCount() {
        return comboBox.getSelectedIndex();
    }

}

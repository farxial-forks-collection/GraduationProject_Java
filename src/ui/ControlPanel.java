package ui;

import main.Constant;
import listener.TimeListener;
import component.MyButton;
import component.MyComboBox;

import javax.swing.*;
import java.awt.*;

/**
 * @author ypl
 * @date 2020/1/9
 */
public class ControlPanel extends JPanel implements Constant {

    private MyComboBox hourComboBox;
    private MyComboBox minuteComboBox;
    private MyComboBox secondsComboBox;
    private JButton setTimeButton, stopButton, takePhotoButton;

    /**
     * 构造函数
     *
     * @param width 面板的宽
     * @param height 面板的高
     * @param tcpUi 主面板
     */
    public ControlPanel(int width, int height, TcpUi tcpUi) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout());

        // 初始化组件
        JPanel autoModePanel = initAutoModePanel(width);
        JPanel manualModePanel = initManualModePanel(width);
        JButton clearButton = new MyButton(140, 30, "清空信息栏", CLEAR_ACTION);
        JButton openDirectoryButton = new MyButton(140, 30, "显示接收到的图片", OPEN_DIRECTORY_ACTION);

        // 为所有组件添加监听器
        TimeListener timeListener = new TimeListener();
        hourComboBox.addActionListener(timeListener);
        minuteComboBox.addActionListener(timeListener);
        secondsComboBox.addActionListener(timeListener);
        setTimeButton.addActionListener(timeListener);
        stopButton.addActionListener(timeListener);
        takePhotoButton.addActionListener(timeListener);
        clearButton.addActionListener(timeListener);
        openDirectoryButton.addActionListener(timeListener);

        // Add Component
        this.add(autoModePanel);
        this.add(manualModePanel);
        this.add(clearButton);
        this.add(openDirectoryButton);
    }

    /**
     * 初始化自动模式的面板
     *
     * @param width 宽
     * @return 初始化后的面板
     */
    private JPanel initAutoModePanel(int width) {
        hourComboBox = new MyComboBox(70, 20, 24, 0, HOUR_ACTION);
        minuteComboBox = new MyComboBox(70, 20, 60, 1, MINUTE_ACTION);
        secondsComboBox = new MyComboBox(70, 20, 60, 0, SECOND_ACTION);
        setTimeButton = new MyButton(140, 30, "设置自动模式时间", SET_TIME_ACTION);
        stopButton = new MyButton(140, 30, "停止自动模式", STOP_ACTION);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, 180));
        panel.setBorder(BorderFactory.createTitledBorder("自动模式"));
        panel.add(new JLabel("小时:    "));
        panel.add(hourComboBox);
        panel.add(new JLabel("分钟:    "));
        panel.add(minuteComboBox);
        panel.add(new JLabel("秒:      "));
        panel.add(secondsComboBox);
        panel.add(setTimeButton);
        panel.add(stopButton);
        return panel;
    }

    /**
     * 初始化手动模式面板
     *
     * @param width 宽
     * @return 初始化之后的面板
     */
    private JPanel initManualModePanel(int width) {
        takePhotoButton = new MyButton(140, 30, "立即拍照", TAKE_PHOTO_ACTION);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, 68));
        panel.setBorder(BorderFactory.createTitledBorder("手动模式"));
        panel.add(takePhotoButton);
        return panel;
    }

}

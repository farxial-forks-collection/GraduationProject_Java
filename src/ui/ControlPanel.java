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
        JButton clearButton = new MyButton(140, 30, "Clear Message", CLEAR_ACTION);
        JButton openDirectoryButton = new MyButton(140, 30, "Open Saved Photo", OPEN_DIRECTORY_ACTION);

        // 为所有组件添加监听器
        TimeListener timeListener = new TimeListener(tcpUi);
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
//        this.add(new SpacePanel(width, 10));
        this.add(clearButton);
//        this.add(new SpacePanel(width, 10));
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
        setTimeButton = new MyButton(140, 30, "Set Time", SET_TIME_ACTION);
        stopButton = new MyButton(140, 30, "Stop Auto Mode", STOP_ACTION);
        takePhotoButton = new MyButton(140, 30, "Manual TakePhoto", TAKE_PHOTO_ACTION);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, 210));
        panel.setBorder(BorderFactory.createTitledBorder("Auto Mode"));
        panel.add(new JLabel("  Hour: "));
        panel.add(hourComboBox);
        panel.add(new JLabel("Minute: "));
        panel.add(minuteComboBox);
        panel.add(new JLabel("Second: "));
        panel.add(secondsComboBox);
        panel.add(setTimeButton);
        panel.add(stopButton);
//        panel.add(new SpacePanel(width - 40, 10));
        panel.add(takePhotoButton);
        return panel;
    }

}

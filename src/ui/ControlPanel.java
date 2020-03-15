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

    /**
     * 构造函数
     *
     * @param width 面板的宽
     * @param height 面板的高
     * @param tcpUI
     */
    public ControlPanel(int width, int height, TcpUi tcpUI) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.WHITE);
        this.setLayout(new FlowLayout());

        // 初始化组件
        JComboBox<Integer> hourComboBox = new MyComboBox(70, 20, 24, 0, HOUR_ACTION);
        JComboBox<Integer> minuteComboBox = new MyComboBox(70, 20, 60, 1, MINUTE_ACTION);
        JComboBox<Integer> secondsComboBox = new MyComboBox(70, 20, 60, 0, SECOND_ACTION);
        JButton setTimeButton = new MyButton(140, 20, "Set Time", SET_TIME_ACTION);
        JButton stopButton = new MyButton(140, 20, "Stop Auto Mode", STOP_ACTION);
        JButton takePhotoButton = new MyButton(140, 20, "Manual TakePhoto", TAKE_PHOTO_ACTION);
        JButton clearButton = new MyButton(140, 20, "Clear Message", CLEAR_ACTION);
        JButton openDirectoryButton = new MyButton(140, 20, "Open Saved Photo", OPEN_DIRECTORY_ACTION);

        // 为所有组件添加监听器
        TimeListener timeListener = new TimeListener(tcpUI);
        hourComboBox.addActionListener(timeListener);
        minuteComboBox.addActionListener(timeListener);
        secondsComboBox.addActionListener(timeListener);
        setTimeButton.addActionListener(timeListener);
        stopButton.addActionListener(timeListener);
        takePhotoButton.addActionListener(timeListener);
        clearButton.addActionListener(timeListener);
        openDirectoryButton.addActionListener(timeListener);

        // Add Component
        this.add(new JLabel("  Hour: "));
        this.add(hourComboBox);
        this.add(new JLabel("Minute: "));
        this.add(minuteComboBox);
        this.add(new JLabel("Second: "));
        this.add(secondsComboBox);
        this.add(setTimeButton);
        this.add(stopButton);
        this.add(new SpacePanel(width, 10, Color.WHITE));
        this.add(takePhotoButton);
        this.add(new SpacePanel(width, 10, Color.WHITE));
        this.add(clearButton);
        this.add(new SpacePanel(width, 10, Color.WHITE));
        this.add(openDirectoryButton);
    }

}

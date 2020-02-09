package ui;

import component.MyTextArea;
import listener.TimeListener;
import main.Constant;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedOutputStream;

/**
 * @author ypl
 * @date 2020/1/7
 */
public class TcpUI implements Constant {

    private JFrame frame;
    private JPanel photoPanel;
    private MessagePanel messagePanel;
    private TimeListener timeListener;

    public void initUi() {
        // 设置UI的风格为系统默认风格(跨平台的太丑了)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        // 主容器
        frame = new JFrame();
        frame.setSize(812, 637);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        // container大小为798*600 (frame为812*637)
        Container container = frame.getContentPane();

        // 左边图片面板
        photoPanel = new PhotoPanel(640, 480);
        // 右边控制面板
        JPanel rightPanel = new RightPanel(158, 480, this);
        // 底部信息面板
        messagePanel = new MessagePanel(798, 120, new MyTextArea());

        // 添加所有二级面板
        container.add(photoPanel, BorderLayout.WEST);
        container.add(rightPanel, BorderLayout.EAST);
        container.add(messagePanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        System.out.println(container.getWidth() + " " + container.getHeight());
    }

    // 以下为外部可调用方法 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public void setPhoto() {
        photoPanel.repaint();
    }

    public void clearMessage() {
        messagePanel.clear();
    }

    public void printMessage(String message) {
        messagePanel.append(message);
    }

    public void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public void setBufferedOutputStream(BufferedOutputStream bufferedOutputStream) {
        timeListener.setBufferedOutputStream(bufferedOutputStream);
    }

}

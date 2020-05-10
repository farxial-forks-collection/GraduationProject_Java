package ui;

import component.MyTextArea;
import listener.TimeListener;
import main.Constant;
import tools.TerminalTools;
import tools.UiTools;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedOutputStream;

/**
 * @author ypl
 * @date 2020/1/7
 */
public class TcpUi implements Constant {

    private static JFrame frame;
    private static JPanel photoPanel;
    private static MessagePanel messagePanel;
    private static TimeListener timeListener;

    // 设置UI的风格为系统默认风格(跨平台的太丑了)
    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void initUi() {
        // 主容器
        frame = new JFrame();
        frame.setTitle("HUNNU.YuanPeiLin.GraduationProject");
        frame.setSize(838, 643);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        // container大小为824*606 (frame为838*643)
        Container container = frame.getContentPane();

        // 左边图片面板
        photoPanel = new PhotoPanel(644, 484);
        // 右边控制面板
        JPanel rightPanel = new RightPanel(UiTools.WIDTH_OFFSET + 180, 484);
        // 底部信息面板
        messagePanel = new MessagePanel(824, UiTools.HEIGHT_OFFSET + 122, new MyTextArea());

        // 添加所有二级面板
        container.add(photoPanel, BorderLayout.WEST);
        container.add(rightPanel, BorderLayout.EAST);
        container.add(messagePanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        // 设置图标
        ImageIcon icon = new ImageIcon("./logo.png");
        frame.setIconImage(icon.getImage());

        // 调试信息
        TerminalTools.print(this.getClass(), "width: " + container.getWidth() + "   height: " + container.getHeight());
        TerminalTools.print(this.getClass(), "width_offset: " + UiTools.WIDTH_OFFSET + "   height_offset: " + UiTools.HEIGHT_OFFSET);
    }

    // 以下为外部可调用方法 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * 收到图片后, 调用图片面板的repaint()刷新图片
     */
    public static void setPhoto() {
        photoPanel.repaint();
    }

    /**
     * 清除底部信息栏的消息
     */
    public static void clearMessage() {
        messagePanel.clear();
    }

    /**
     * 在底部信息栏添加消息
     *
     * @param message 要添加的消息(无需换行符, 自动换行)
     */
    public static void printMessage(String message) {
        messagePanel.append(message);
    }

    /**
     * 显示一个警告框
     *
     * @param message 警告框的提示文字
     */
    public static void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

}

package ui;

import component.MyTextArea;
import listener.TimeListener;
import main.Constant;
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
        frame.setTitle("Hunnu.yuanpeilin.graduationProject");
        frame.setSize(818 + 20, 643);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        // container大小为798*600 (frame为812*637)
        Container container = frame.getContentPane();

        // 左边图片面板
        photoPanel = new PhotoPanel(644, 484);
        photoPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        // 右边控制面板
        JPanel rightPanel = new RightPanel(UiTools.WIDTH_OFFSET + 160 + 20, 484, this);
        // 底部信息面板
        messagePanel = new MessagePanel(804 + 20, UiTools.HEIGHT_OFFSET + 122, new MyTextArea());

        // 添加所有二级面板
        container.add(photoPanel, BorderLayout.WEST);
        container.add(rightPanel, BorderLayout.EAST);
        container.add(messagePanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        // 设置图标
        ImageIcon icon = new ImageIcon("./logo.png");
        frame.setIconImage(icon.getImage());

        System.out.println("width: " + container.getWidth() + "   height: " + container.getHeight());
        System.out.println("width_offset: " + UiTools.WIDTH_OFFSET + "   height_offset: " + UiTools.HEIGHT_OFFSET);
    }

    // 以下为外部可调用方法 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public static void setPhoto() {
        photoPanel.repaint();
    }

    public static void clearMessage() {
        messagePanel.clear();
    }

    public static void printMessage(String message) {
        messagePanel.append(message);
    }

    public static void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public void setBufferedOutputStream(BufferedOutputStream bufferedOutputStream) {
        timeListener.setBufferedOutputStream(bufferedOutputStream);
    }

}

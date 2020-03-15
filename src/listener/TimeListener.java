package listener;

import main.Constant;
import tcpthread.MyTimerTask;
import tools.FileTools;
import tools.OsTools;
import ui.TcpUi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author ypl
 * @date 2020/1/7
 */
public class TimeListener implements ActionListener, Constant {

    private int hour = 0, minute = 1, second = 0;
    private BufferedOutputStream bufferedOutputStream;
    private TcpUi tcpUI;
    private Timer timer;
    private TimerTask timerTask;

    public TimeListener(TcpUi tcpUI) {
        this.tcpUI = tcpUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case HOUR_ACTION:
                hourAction((JComboBox<Integer>) e.getSource());
                break;
            case MINUTE_ACTION:
                minuteAction((JComboBox<Integer>) e.getSource());
                break;
            case SECOND_ACTION:
                secondAction((JComboBox<Integer>) e.getSource());
                break;
            case SET_TIME_ACTION:
                setTimeAction();
                break;
            case STOP_ACTION:
                stopAction();
                break;
            case TAKE_PHOTO_ACTION:
                takePhotoAction();
                break;
            case CLEAR_ACTION:
                clearAction();
                break;
            case OPEN_DIRECTORY_ACTION:
                openDirectoryAction();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + e.getActionCommand());
        }
    }

    // 以下为内部函数, 相应事件监听 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private void hourAction(JComboBox<Integer> comboBox) {
        hour = comboBox.getSelectedIndex();
    }

    private void minuteAction(JComboBox<Integer> comboBox) {
        minute = comboBox.getSelectedIndex();
    }

    private void secondAction(JComboBox<Integer> comboBox) {
        second = comboBox.getSelectedIndex();
    }

    private void setTimeAction() {
        // 取消上一次设置的时间
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        timer = new Timer();
        long period = (hour * 3600 + minute * 60 + second) * 1000;
        timerTask = new MyTimerTask(tcpUI);
        // 开始执行定时任务 延时0ms 每次任务的间隔周期为 period ms
        timer.schedule(timerTask, 0, period);
    }

    private void stopAction() {
        if (timer != null) {
            // 此时为自动模式, 执行完下面三句会变成手动模式
            timer.cancel();
            timer = null;
            tcpUI.printMessage("Canceled Auto Mode");
        } else {
            // 此时为手动模式(模式不会改变)
            tcpUI.printMessage("Already In Manual Mode");
        }
    }

    private void takePhotoAction() {
        tcpUI.printMessage("Manual Take Photo");
    }

    private void clearAction() {
        tcpUI.clearMessage();
    }

    private void openDirectoryAction(){
        FileTools.showFile(OsTools.getOS(), FileTools.getPicturesFolder());
    }

    // 以下为外部调用的方法 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public void setBufferedOutputStream(BufferedOutputStream bufferedOutputStream) {
        this.bufferedOutputStream = bufferedOutputStream;
    }

}

package listener;

import main.Constant;
import tcpthread.MyTimerTask;
import tools.FileTools;
import tools.OsTools;
import tools.TakePhotoTools;
import ui.TcpUi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author ypl
 * @date 2020/1/7
 */
public class TimeListener implements ActionListener, Constant {

    private int hour = 0, minute = 1, second = 0;
    private Timer timer;

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

    /**
     * 设置小时
     *
     * @param comboBox 小时下拉框
     */
    private void hourAction(JComboBox<Integer> comboBox) {
        hour = comboBox.getSelectedIndex();
    }

    /**
     * 设置分钟
     *
     * @param comboBox 分钟下拉框
     */
    private void minuteAction(JComboBox<Integer> comboBox) {
        minute = comboBox.getSelectedIndex();
    }

    /**
     * 设置秒
     *
     * @param comboBox 秒下拉框
     */
    private void secondAction(JComboBox<Integer> comboBox) {
        second = comboBox.getSelectedIndex();
    }

    /**
     * <p>设置自动模式的时间</p>
     * 如果已经是自动模式, 会覆盖上一次自动模式设置的时间
     */
    private void setTimeAction() {
        // 取消上一次设置的时间
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        if (hour == 0 && minute == 0 && second == 0) {
            TcpUi.showWarningMessage("请选择正确的时间!");
        } else {
            if (hour == 0 && minute == 0 && second < 20) {
                TcpUi.showWarningMessage("由于单片机速度不快， 时间过短可能会造成错误！");
            }
            timer = new Timer();
            long period = (hour * 3600 + minute * 60 + second) * 1000;
            TimerTask timerTask = new MyTimerTask();
            // 开始执行定时任务 延时0ms 每次任务的间隔周期为 period ms
            timer.schedule(timerTask, 0, period);
        }
    }

    /**
     * 取消自动模式
     */
    private void stopAction() {
        if (timer != null) {
            // 此时为自动模式, 执行完下面三句会变成手动模式
            timer.cancel();
            timer = null;
            TcpUi.printMessage("已取消自动模式");
        } else {
            // 此时为手动模式(模式不会改变)
            TcpUi.printMessage("已经是手动模式");
        }
    }

    /**
     * 手动拍照
     */
    private void takePhotoAction() {
        TcpUi.printMessage("[手动模式] 已发送拍照请求，稍等几秒");
        TakePhotoTools.sendTakePhotoCommand();
    }

    /**
     * 清除底部信息栏
     */
    private void clearAction() {
        TcpUi.clearMessage();
    }

    /**
     * 打开保存图片的文件夹
     */
    private void openDirectoryAction() {
        FileTools.showFile(FileTools.getPicturesFolder());
    }

}

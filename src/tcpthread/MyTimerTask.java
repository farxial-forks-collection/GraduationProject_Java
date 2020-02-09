package tcpthread;

import ui.TcpUI;

import java.util.TimerTask;

/**
 * @author ypl
 * @date 2020/1/10
 */
public class MyTimerTask extends TimerTask {

    private int i = 0;
    private TcpUI tcpUI;

    public MyTimerTask(TcpUI tcpUI) {
        this.tcpUI = tcpUI;
    }

    @Override
    public void run() {
        i++;
        tcpUI.printMessage("Auto Photo Mode: " + i);
    }

}

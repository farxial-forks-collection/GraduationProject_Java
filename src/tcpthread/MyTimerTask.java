package tcpthread;

import tools.TakePhotoTools;
import ui.TcpUi;

import java.util.TimerTask;

/**
 * @author ypl
 * @date 2020/1/10
 */
public class MyTimerTask extends TimerTask {

    private int i = 0;

    @Override
    public void run() {
        i++;
        TcpUi.printMessage("[自动模式" + i + "] 已发送拍照请求，稍等几秒");
        TakePhotoTools.sendTakePhotoCommand();
    }

}

package tcpthread;

import main.Constant;
import tools.IOTools;
import tools.OtherTools;
import ui.TcpUI;

import java.io.*;

/**
 * @author ypl
 * @date 2020/1/4
 */
public class ReceiveThread extends Thread implements Constant {

    /**
     * 每个包的大小
     */
    private final int PACKAGE_SIZE = 1000;
    /**
     * 包的数量
     */
    private final int PACKAGE_COUNT = 16;
    /**
     * 接受的总字节数
     */
    private final int TOTAL_SIZE = PACKAGE_SIZE * PACKAGE_COUNT;
    private static final String FORMED_PHOTO_PATH = "receive.jpg";
    BufferedInputStream bufferedInputStream;
    BufferedOutputStream bufferedOutputStream;
    TcpUI tcpui;

    public ReceiveThread(BufferedInputStream bufferedInputStream, BufferedOutputStream bufferedOutputStream, TcpUI tcpui) {
        this.bufferedInputStream = bufferedInputStream;
        this.bufferedOutputStream = bufferedOutputStream;
        this.tcpui = tcpui;
    }

    @Override
    public void run() {
        byte[] bytes, pack;
        int point_bytes, pack_count;
        int[] position; // 长度为2, 存储point_ffd8和point_ffd9
        File formedPhoto = new File(FORMED_PHOTO_PATH);
        while (true) {
            // 初始化变量
            bytes = new byte[TOTAL_SIZE];
            pack = new byte[PACKAGE_SIZE];
            point_bytes = 0;
            pack_count = 0;

            // 每次循环接受一个包
            try {
                while (pack_count < PACKAGE_COUNT) {
                    pack_count++;
                    bufferedInputStream.read(pack);
                    for (int i = 0; i < pack.length; i++) {
                        bytes[point_bytes] = pack[i];
                        point_bytes++;
                    }
                    System.out.println(pack_count + " package receive success!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            tcpui.printMessage(PACKAGE_SIZE + "*" + PACKAGE_COUNT + "  Photo received");

            // 处理数据
            position = OtherTools.getFFD8AndFFD9(bytes);
            // 生成图片
            IOTools.writeIntoFile(formedPhoto, bytes, position[0], position[1] - position[0] + 1, false);

            // 显示图片
            tcpui.setBufferedOutputStream(bufferedOutputStream);
            tcpui.setPhoto();
        }
    }

}

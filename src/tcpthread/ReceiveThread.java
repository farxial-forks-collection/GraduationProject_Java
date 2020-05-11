package tcpthread;

import main.Constant;
import tools.*;
import ui.TcpUi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author ypl
 * @date 2020/1/4
 */
public class ReceiveThread extends Thread implements Constant {

    BufferedInputStream bufferedInputStream;
    BufferedOutputStream bufferedOutputStream;

    public ReceiveThread(BufferedInputStream bufferedInputStream, BufferedOutputStream bufferedOutputStream) {
        this.bufferedInputStream = bufferedInputStream;
        this.bufferedOutputStream = bufferedOutputStream;
        TakePhotoTools.setBufferedOutputStream(bufferedOutputStream);
    }

    @Override
    public void run() {
        byte[] bytes, pack;
        int point_bytes, pack_count;
        // 长度为2, 存储point_ffd8和point_ffd9
        int[] position;
        // 接受的总字节数
        int TOTAL_SIZE = PACKAGE_SIZE * PACKAGE_COUNT;
        File formedPhoto = new File(FileTools.getNewPhotoPath());
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
                    TerminalTools.print(this.getClass(), "第 " + pack_count + " 个包接收成功!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            TcpUi.printMessage("总共接收: " + PACKAGE_SIZE + "(包的大小) * " + PACKAGE_COUNT + "(包的数量)");

            // 处理数据
            position = OtherTools.getFFD8AndFFD9(bytes);
            // 生成图片
            IoTools.writeIntoFile(formedPhoto, bytes, position[0], position[1] - position[0] + 1, false);

            // 显示图片
            TcpUi.setPhoto();
            TcpUi.printMessage("图片已保存: " + FileTools.getLastPhotoPath());
        }
    }

}

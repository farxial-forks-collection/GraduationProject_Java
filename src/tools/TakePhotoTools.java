package tools;

import java.io.BufferedOutputStream;
import java.io.IOException;

/**
 * @author TeLa LuoSiFen
 * @date 2020/3/27
 */
public class TakePhotoTools {

    private static BufferedOutputStream bufferedOutputStream;

    /**
     * 向单片机发送拍照的命令
     */
    public static void sendTakePhotoCommand() {
        if (bufferedOutputStream != null) {
            try {
                bufferedOutputStream.write("mmmmmmmmmmmmmmmm".getBytes());
                bufferedOutputStream.flush();
                TerminalTools.print(TakePhotoTools.class, "已发送收到拍照的命令");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setBufferedOutputStream(BufferedOutputStream bufferedOutputStream) {
        TakePhotoTools.bufferedOutputStream = bufferedOutputStream;
    }
}

package tools;

import java.io.*;

/**
 * @author ypl
 * @date 2019/12/28
 */
public class IoTools {

    public static void writeIntoFile(File file, byte[] bytes, int off, int len, boolean append) {
        OutputStream outputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            outputStream = new FileOutputStream(file, append);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            bufferedOutputStream.write(bytes, off, len);
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

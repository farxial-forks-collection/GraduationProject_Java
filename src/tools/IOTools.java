package tools;

import java.io.*;

/**
 * @author ypl
 * @date 2019/12/28
 */
public class IOTools {

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

    public static void writeIntoFile(File file, byte[] bytes, boolean append) {
        writeIntoFile(file, bytes, 0, bytes.length, append);
    }

    public static void writeIntoFile(File file, String s, boolean append) {
        writeIntoFile(file, s.getBytes(), append);
    }

    public static void sendToSingleChip(BufferedOutputStream bufferedOutputStream, String message) {
        try {
            bufferedOutputStream.write(message.getBytes());
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取一个文件
     *
     * @param file
     * @return 读出来的字节范围在 -128~127 之间
     */
    public static byte[] readFile(File file) {
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        byte[] bytes = null;
        try {
            inputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(inputStream);
            bytes = new byte[inputStream.available()];
            bufferedInputStream.read(bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

}

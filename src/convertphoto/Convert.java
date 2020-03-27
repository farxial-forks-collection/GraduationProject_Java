package convertphoto;

import tools.FileTools;
import tools.IoTools;
import tools.OsTools;
import tools.OtherTools;

import java.io.*;


/**
 * 本类实现两个功能: jpg转txt 以及 txt转jpg
 *
 * @author ypl
 * @date 2019/12/28
 */
public class Convert {

    /**
     * <p>读取一个图片并将ASCII码存入文件</p>
     * <p>方法是:
     * 每次读取一个字节, 将这个字节转换为两个十六进制数</p>
     *
     * @param absolutePath jpg文件的绝对路径
     * @param needComma 是否需要空格与逗号
     * @param maxLineCount 每行最大的数据量(字节数)
     */
    public static void getTxtByJpg(String absolutePath, boolean needComma, int maxLineCount) {
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            fileInputStream = new FileInputStream(absolutePath);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            fileOutputStream = new FileOutputStream(FileTools.getTxtPath(absolutePath));
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            // 当前行的字节数
            int count = 0;
            int i;
            String temp;
            boolean isSecondByte = false;
            // 每行最大字节数要是偶数才行, 下面是将每行最大字节数转换为偶数
            if (!needComma && maxLineCount % 2 != 0) {
                maxLineCount++;
            }
            while ((i = bufferedInputStream.read()) != -1) {
                // 将一个字节转为两个16进制数
                temp = OtherTools.getHexByOneByte(i, needComma, needComma);
                bufferedOutputStream.write(temp.getBytes());
                bufferedOutputStream.flush();
                // 如果不需要逗号, 每两个字节隔一个空格
                if (!needComma && isSecondByte) {
                    bufferedOutputStream.write(" ".getBytes());
                    bufferedOutputStream.flush();
                }
                isSecondByte = !isSecondByte;
                count++;
                // 超过最大数据量, 换行
                if (count >= maxLineCount) {
                    bufferedOutputStream.write(OsTools.getLineSeparator().getBytes());
                    bufferedOutputStream.flush();
                    count = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <p>将txt文件转换成图片(txt文件只能包含数据与空格)</p>
     * <p>方法是: 每次读取一行(读取到的是十六进制的字符, 即实际内容, 而不是字节), 得到所有行,
     * 去掉空格, 得到一个很长的字符串, 然后每两个十六进制字符转化为字节类型</p>
     *
     * @param txtAbsolutePath txt文件绝对路径
     */
    public static void getJpgByTxt(String txtAbsolutePath) {
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            File txtFile = new File(txtAbsolutePath);
            fileInputStream = new FileInputStream(txtFile);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            StringBuilder oneNoSpaceLine;
            StringBuilder allLine = new StringBuilder();
            // 每次处理一行
            while ((line = bufferedReader.readLine()) != null) {
                oneNoSpaceLine = new StringBuilder();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) != ' ') {
                        oneNoSpaceLine.append(line.charAt(i));
                    }
                }
                allLine.append(oneNoSpaceLine);
            }
            // 得到所有的行, 并去掉了空格
            String s = allLine.toString();

            // 十六进制转换成byte类型
            byte[] bytes = new byte[s.length() / 2];
            String temp;
            for (int i = 0; i < bytes.length; i++) {
                temp = s.substring(i * 2, i * 2 + 2);
                bytes[i] = (byte) Integer.parseInt(temp, 16);
            }

            // 处理数据
            int[] points = OtherTools.getFFD8AndFFD9(bytes);
            // 写入数据
            File jpgFile = new File(FileTools.getJpgPath(txtAbsolutePath));
            IoTools.writeIntoFile(jpgFile, bytes, points[0], points[1] - points[0] + 1, false);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

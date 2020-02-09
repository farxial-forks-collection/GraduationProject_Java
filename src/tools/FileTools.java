package tools;

import main.Constant;

import java.io.IOException;

/**
 * @author ypl
 * @date 2019/12/28
 */
public class FileTools implements Constant {

    /**
     * 弹出文件管理器
     *
     * @param os        操作系统
     * @param directory 传入一个目录, 不是绝对路径
     */
    public static void showFile(boolean os, String directory) {
        try {
            if (os == LINUX) {
                Runtime.getRuntime().exec("nautilus " + directory);
            }
            if (os == WINDOWS) {
                Runtime.getRuntime().exec("cmd /c start explorer " + directory);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>将一个绝对路径拆分成目录, 文件名(不包含点)</p>
     * <p>传入"home/root/Desktop/Main.java", 返回{"home/root/Desktop", "Main"}</p>
     *
     * @param absolutePath 某个绝对路径
     * @return 返回一个数组, 长度为2  {目录, 文件名}
     */
    private static String[] splitPath(String absolutePath) {
        // 得到.的位置和最后一个文件分隔符的位置
        int point_dot = -1, point_slash = -1;
        // 从后往前遍历
        for (int i = absolutePath.length() - 1; i >= 0; i--) {
            char c = absolutePath.charAt(i);
            if (point_dot == -1 && c == '.') {
                point_dot = i;
            }
            if (point_slash == -1 && c == OS.separateSymbol) {
                point_slash = i;
            }
            // 位置都找到了之后就退出
            if (point_dot != -1 && point_slash != -1) {
                break;
            }
        }
        String[] strings = new String[2];
        // directory
        strings[0] = absolutePath.substring(0, point_slash + 1);
        // file name
        strings[1] = absolutePath.substring(point_slash + 1, point_dot);
        return strings;
    }

    /**
     * <p>根据绝对路径获取目录路径</p>
     * <p>(通过调用内部函数splitPath(String absolutePath)实现)</p>
     * <p>传入"home/root/Desktop/Main.java", 返回"home/root/Desktop"</p>
     *
     * @param absolutePath 某个文件的绝对路径
     * @return 此文件所在目录的路径
     */
    public static String getDirByPath(String absolutePath) {
        String[] strings = splitPath(absolutePath);
        return strings[0];
    }

    /**
     * <p>根据绝对路径获取文件名(不包含扩展名和点)</p>
     * <p>(通过调用内部函数splitPath(String absolutePath)实现)</p>
     * <p>传入"home/root/Desktop/Main.java", 返回"Main.java"</p>
     *
     * @param absolutePath 某个文件的绝对路径
     * @return 此文件的文件名
     */
    public static String getFileNameByPath(String absolutePath) {
        String[] strings = splitPath(absolutePath);
        return strings[1];
    }

    /**
     * <p>得到同名txt文件</p>
     * <p>(通过调用内部函数splitPath(String absolutePath)实现)</p>
     * <p>传入"home/root/Desktop/Main.java", 返回"Main.txt"</p>
     *
     * @param absolutePath 某个文件的绝对路径
     * @return 此文件同名的txt文件
     */
    public static String getTxtPath(String absolutePath) {
        String[] strings = splitPath(absolutePath);
        return strings[0] + strings[1] + ".txt";
    }

    /**
     * <p>得到jpg同名文件</p>
     * <p>(通过调用内部函数splitPath(String absolutePath)实现)</p>
     * <p>传入"home/root/Desktop/Main.java", 返回"Main.jpg"</p>
     *
     * @param absolutePath 某个文件的绝对路径
     * @return 此文件同名的jpg文件
     */
    public static String getJpgPath(String absolutePath) {
        String[] strings = splitPath(absolutePath);
        return strings[0] + strings[1] + ".jpg";
    }

}

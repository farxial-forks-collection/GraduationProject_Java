package tools;

import main.Constant;

import java.util.regex.Pattern;

/**
 * @author ypl
 * @date 2020/1/4
 */
public class OS implements Constant {

    private static boolean os;
    /**
     * 换行符
     */
    public static String lineBreakSymbol;
    /**
     * 文件分割符
     */
    public static char separateSymbol;

    private static final String WINDOWS_REGEX = "[wW][iI][nN]";

    static {
        // 获取操作系统名称
        String osName = System.getProperty("os.name");
        if (Pattern.matches(WINDOWS_REGEX, osName.substring(0, 3))) {
            setAsWindows();
        } else {
            setAsLinux();
        }
    }

    /**
     * 设置当前的os为Linux
     */
    public static void setAsLinux() {
        os = LINUX;
        lineBreakSymbol = "\n";
        separateSymbol = '/';
    }

    /**
     * 设置当前的os为Windows
     */
    public static void setAsWindows() {
        os = WINDOWS;
        lineBreakSymbol = "\r\n";
        separateSymbol = '\\';
    }

    public static boolean getOS() {
        return os;
    }

}

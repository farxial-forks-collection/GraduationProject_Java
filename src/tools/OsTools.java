package tools;

import main.Constant;

import java.util.regex.Pattern;

/**
 * standard: 798*600
 * <p>Win Oracle JDK 11: 798*600</p>
 * <p>Win Oracle JDK 8: 806*602</p>
 * <p>Linux Oracle JDK 8: 812*600</p>
 * <p>Linux Oracle JDK 11: 812*600</p>
 * <p>Linux Open JDK 11: 812*600</p>
 *
 * @author ypl
 * @date 2020/1/4
 */
public class OsTools implements Constant {

    private static boolean os;
    private static String lineSeparator;
    private static char fileSeparator;
    private static final String WINDOWS_REGEX = "[wW][iI][nN]";

    // 获取操作系统名称
    static {
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
        lineSeparator = "\n";
        fileSeparator = '/';
    }

    /**
     * 设置当前的os为Windows
     */
    public static void setAsWindows() {
        os = WINDOWS;
        lineSeparator = "\r\n";
        fileSeparator = '\\';
    }

    public static boolean getOS() {
        return os;
    }

    public static String getLineSeparator() {
        return lineSeparator;
    }

    public static char getFileSeparator() {
        return fileSeparator;
    }

    // ========================================================================

    /**
     * 获取用户的家目录
     *
     * @return 家目录路径
     */
    public static String getUserHomeDir() {
        return System.getProperty("user.home");
    }

}

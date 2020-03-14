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
public class OSTools implements Constant {

    private static boolean os;
    private static String lineSeparator;
    private static char fileSeparator;
    private static final String WINDOWS_REGEX = "[wW][iI][nN]";

    public static int WIDTH_OFFSET = 0;
    public static int HEIGHT_OFFSET = 0;

    static {
        // 获取操作系统名称
        String osName = System.getProperty("os.name");
        if (Pattern.matches(WINDOWS_REGEX, osName.substring(0, 3))) {
            setAsWindows();
            windowsUiFix();
        } else {
            setAsLinux();
            linuxUiFix();
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

    /**
     * linux下修复ui的方法
     */
    private static void linuxUiFix() {
        WIDTH_OFFSET += 14;
    }

    /**
     * windows下修复ui的方法
     */
    private static void windowsUiFix() {
        // 获取当前java的版本
        String version = System.getProperty("java.specification.version");
        System.out.println("Java version: " + version);
        switch (version) {
            case "1.8":
                HEIGHT_OFFSET += 2;
                WIDTH_OFFSET += 8;
                break;
            case "11":
            default:
                HEIGHT_OFFSET += 0;
                WIDTH_OFFSET += 0;
        }
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

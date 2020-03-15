package tools;

/**
 * @author TeLa LuoSiFen
 * @date 2020/3/15
 */
public class UiTools {

    public static int WIDTH_OFFSET;
    public static int HEIGHT_OFFSET;

    static {
        if (OsTools.getOS() == OsTools.LINUX) {
            linuxUiFix();
        } else {
            windowsUiFix();
        }
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
        switch (JavaVersionTools.getVersion()) {
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

}

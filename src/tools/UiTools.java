package tools;

/**
 * <p>11: 798*600</p>
 * <p>8: 806*602</p>
 *
 * @author TeLa LuoSiFen
 * @date 2020/3/14
 */
public class UiTools {

    public static int WIDTH_OFFSET;
    public static int HEIGHT_OFFSET;

    static {
        String version = System.getProperty("java.specification.version");
        System.out.println("Java version: " + version);
        switch (version) {
            case "1.8":
                UiTools.HEIGHT_OFFSET = 2;
                UiTools.WIDTH_OFFSET = 8;
                break;
            case "11":
            default:
                UiTools.HEIGHT_OFFSET = 0;
                UiTools.WIDTH_OFFSET = 0;
        }
    }

}

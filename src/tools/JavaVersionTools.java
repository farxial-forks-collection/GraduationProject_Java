package tools;

/**
 * @author TeLa LuoSiFen
 * @date 2020/3/15
 */
public class JavaVersionTools {

    private static String version;

    // 获取当前java的版本
    static {
        version = System.getProperty("java.specification.version");
        System.out.println("Java version: " + version);
    }

    public static String getVersion() {
        return version;
    }

}

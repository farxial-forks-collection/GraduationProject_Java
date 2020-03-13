package tools;

/**
 * @author TeLa LuoSiFen
 * @date 2020/3/13
 */
public class NameTools {

    public static final String DEFAULT_NAME = "receive.jpg";
    private static String name;

    /**
     * <p>生成一个新的图片名字</p>
     * 例如: receive_2020_03_13_15_23_25.jpg
     *
     * @return 新图片的文件名(包括后缀)
     */
    public static String getNewName() {
        String date = TimeTools.getDate();
        name = "receive_" + date + ".jpg";
        return name;
    }

    /**
     * <p>获得上一张图片的名字</p>
     * 即最新接收到的图片的名字
     *
     * @return 最新图片的文件名(包括后缀)
     */
    public static String getLastName() {
        if (name != null) {
            return name;
        } else {
            // name==null 说明还没收到图片, 返回默认图片文件名
            return DEFAULT_NAME;
        }
    }

}

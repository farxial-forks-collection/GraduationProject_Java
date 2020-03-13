package tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author TeLa LuoSiFen
 * @date 2020/3/13
 */
public class TimeTools {

    /**
     * <p>获取格式化的时间</p>
     * <p>
     * 例如 2020-03-13 15:23:25 返回 2020_03_13_15_23_25
     * 目的是为了避免文件名中的空格与特殊字符, 避免不必要的麻烦
     * </p>
     *
     * @return 格式化后的时间
     */
    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        return simpleDateFormat.format(date);
    }

}

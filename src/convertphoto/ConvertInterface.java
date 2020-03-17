package convertphoto;

/**
 * @author ypl
 * @date 2019/12/29
 */
public interface ConvertInterface {

    /**
     * 将所有组件设置为可以点击
     */
    void setEnable();

    /**
     * 将所有组件设置为不可点击
     */
    void setUnable();

    /**
     * 得到当前所选择的每行最大数据量
     *
     * @return 当前选择的每行最大数据量
     */
    int getMaxLineCount();

}

package main;

/**
 * @author ypl
 * @date 2020/1/8
 */
public interface Constant {

    boolean WINDOWS = false;
    boolean LINUX = true;

    boolean JPG_TO_TXT = true;
    boolean TXT_TO_JPG = false;

    int FF = -1;
    int D8 = -40;
    int D9 = -39;


    // ConvertPhoto UI & Listener >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    String TO_TXT_ACTION = "to txt action";
    String TO_JPG_ACTION = "to jpg action";
    String LINUX_ACTION = "linux action";
    String WINDOWS_ACTION = "windows action";
    String COMMA_ACTION = "comma action";
    String MAX_LINE_ACTION = "max line action";
    String CHOOSE_FILE_ACTION = "choose file action";


    // TcpServer UI & Listener >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    String HOUR_ACTION = "hour action";
    String MINUTE_ACTION = "minute action";
    String SECOND_ACTION = "second action";
    String SET_TIME_ACTION = "set time action";
    String TAKE_PHOTO_ACTION = "take photo action";
    String CLEAR_ACTION = "clear action";
    String STOP_ACTION = "stop action";
    String OPEN_DIRECTORY_ACTION = "open directory";


    // ConvertListener中的一些状态 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    int UNKNOWN = 0;
    int CONVERT_JPG_TO_TXT_SUCCESS = 1;
    int NOT_JPG = 2;
    int NOT_TXT = 3;
    int CONVERT_TXT_TO_JPG_SUCCESS = 4;

    // Receive Thread >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    /**
     * 每个包的大小
     */
    int PACKAGE_SIZE = 1000;
    /**
     * 包的数量
     */
    int PACKAGE_COUNT = 16;
}

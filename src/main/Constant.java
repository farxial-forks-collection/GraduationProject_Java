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

    String TO_TXT_ACTION = "to txt action";
    String TO_JPG_ACTION = "to jpg action";
    String LINUX_ACTION = "linux action";
    String WINDOWS_ACTION = "windows action";
    String COMMA_ACTION = "comma action";
    String MAX_LINE_ACTION = "max line action";
    String CHOOSE_FILE_ACTION = "choose file action";

    // tcp server >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    String HOUR_ACTION = "hour action";
    String MINUTE_ACTION = "minute action";
    String SECOND_ACTION = "second action";
    String SET_TIME_ACTION = "set time action";
    String TAKE_PHOTO_ACTION = "take photo action";
    String CLEAR_ACTION = "clear action";
    String STOP_ACTION = "stop action";
}

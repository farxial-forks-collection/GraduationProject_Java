package listener;

import convertphoto.Convert;
import convertphoto.ConvertInterface;
import main.Constant;
import tools.FileTools;
import tools.OsTools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.regex.Pattern;

/**
 * @author ypl
 * @date 2019/12/27
 */
public class ConvertListener implements ActionListener, Constant {

    /**
     * 是否需要0x与逗号
     */
    private boolean needComma = false;
    /**
     * 当前选择的每行最大数据量
     */
    private int maxLineCount = 32;
    /**
     * 当前的转换方式
     */
    private boolean method = JPG_TO_TXT;

    private ConvertInterface convert;
    private final int UNKNOWN = 0;
    private final int CONVERT_JPG_TO_TXT_SUCCESS = 1;
    private final int NOT_JPG = 2;
    private final int NOT_TXT = 3;
    private final int CONVERT_TXT_TO_JPG_SUCCESS = 4;

    public ConvertListener(ConvertInterface convertInterface) {
        this.convert = convertInterface;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case TO_TXT_ACTION:
                toTxtAction();
                break;
            case TO_JPG_ACTION:
                toJpgAction();
                break;
            case LINUX_ACTION:
                linuxAction();
                break;
            case WINDOWS_ACTION:
                winAction();
                break;
            case COMMA_ACTION:
                commaAction();
                break;
            case MAX_LINE_ACTION:
                maxLineCountAction();
                break;
            case CHOOSE_FILE_ACTION:
                fileAction();
                break;
            default:
                System.out.println("ERROR! Check Your Code!");
                break;
        }
    }

    private void toTxtAction() {
        method = JPG_TO_TXT;
        convert.setEnable();
    }

    private void toJpgAction() {
        method = TXT_TO_JPG;
        convert.setUnable();
    }

    private void linuxAction() {
        OsTools.setAsLinux();
    }

    private void winAction() {
        OsTools.setAsWindows();
    }

    private void commaAction() {
        needComma = !needComma;
    }

    private void maxLineCountAction() {
        maxLineCount = convert.getMaxLineCount();
    }

    /**
     * <p>通过fileChooser选择文件, 得到文件的名和路径, 再判断文件后缀是否合法,
     * 合法则转换, 不合法则标记一下状态. 最后根据状态做出不同处理</p>
     */
    private void fileAction() {
        // 选择文件
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Choose A Photo");
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.showDialog(null, null);
        // 得到选择的文件
        long past = System.currentTimeMillis();
        File file = fileChooser.getSelectedFile();
        String path = file.getAbsolutePath();
        String dir = FileTools.getDirByPath(path);
        String txtPath = FileTools.getTxtPath(path);
        String jpgPath = FileTools.getJpgPath(path);

        int status = UNKNOWN;
        // 判断合法性
        if (method == JPG_TO_TXT) {
            boolean isPhoto = Pattern.matches(".+\\.[jJ][pP][gG]", path);
            if (isPhoto) {
                Convert.getTxtByJpg(path, needComma, maxLineCount);
                status = CONVERT_JPG_TO_TXT_SUCCESS;
                FileTools.showFile(OsTools.getOS(), dir);
            }
            if (!isPhoto) {
                status = NOT_JPG;
            }
        }
        if (method == TXT_TO_JPG) {
            boolean isTxt = Pattern.matches(".+\\.[tT][xX][tT]", path);
            if (isTxt) {
                Convert.getJpgByTxt(path);
                status = CONVERT_TXT_TO_JPG_SUCCESS;
                FileTools.showFile(OsTools.getOS(), dir);
            }
            if (!isTxt) {
                status = NOT_TXT;
            }
        }

        // 根据相应的状态, 弹出对应的提示框
        String optionPanelMessage;
        long now = System.currentTimeMillis();
        long time = now - past;
        switch (status) {
            case CONVERT_JPG_TO_TXT_SUCCESS:
                optionPanelMessage = "Convert Success" + OsTools.getLineSeparator() + txtPath + OsTools.getLineSeparator() + "time: " + time + "ms";
                break;
            case CONVERT_TXT_TO_JPG_SUCCESS:
                optionPanelMessage = "Convert Success" + OsTools.getLineSeparator() + jpgPath + OsTools.getLineSeparator() + "time: " + time + "ms";
                break;
            case NOT_JPG:
                optionPanelMessage = "Please Select A JPG Photo!";
                break;
            case NOT_TXT:
                optionPanelMessage = "Please Select A TXT File!";
                break;
            case UNKNOWN:
            default:
                optionPanelMessage = "Unknown Error";
                break;
        }
        JOptionPane.showMessageDialog(null, optionPanelMessage, "WARNING", JOptionPane.WARNING_MESSAGE);
    }

}

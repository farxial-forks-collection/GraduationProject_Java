package main;

import tcpthread.ServerThread;
import ui.OldConvertPhotoUI;

/**
 * @author ypl
 * @date 2020/1/4
 */
public class Main {

    public static void main(String[] args) {
        new ServerThread().start();

//        new OldConvertPhotoUI();
    }

}

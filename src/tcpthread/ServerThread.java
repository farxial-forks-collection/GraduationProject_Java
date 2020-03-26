package tcpthread;

import ui.TcpUi;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ypl
 * @date 2020/1/10
 */
public class ServerThread extends Thread {

    private static final int PORT = 8080;

    @Override
    public void run() {
        ServerSocket server = null;
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        OutputStream outputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            server = new ServerSocket(PORT);
            TcpUi ui = new TcpUi();
            ui.initUi();
            ui.printMessage("初始化完成, 等待图片传输...");
            while (true) {
                Socket client = server.accept();
                ui.printMessage("单片机已连接");
                inputStream = client.getInputStream();
                bufferedInputStream = new BufferedInputStream(inputStream);
                outputStream = client.getOutputStream();
                bufferedOutputStream = new BufferedOutputStream(outputStream);
                ui.printMessage("单片机地址: " + client.getRemoteSocketAddress() + "   OutputSteamHashcode: " + bufferedOutputStream.hashCode());
                new ReceiveThread(bufferedInputStream, bufferedOutputStream, ui).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

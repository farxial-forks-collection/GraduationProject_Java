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
        ServerSocket server;
        InputStream inputStream;
        BufferedInputStream bufferedInputStream;
        OutputStream outputStream;
        BufferedOutputStream bufferedOutputStream;
        try {
            server = new ServerSocket(PORT);
            new TcpUi().initUi();
            TcpUi.printMessage("初始化完成, 等待单片机连接...");
            while (true) {
                Socket client = server.accept();
                TcpUi.printMessage("单片机已连接，请等待单片机初始化（30s左右）");
                inputStream = client.getInputStream();
                bufferedInputStream = new BufferedInputStream(inputStream);
                outputStream = client.getOutputStream();
                bufferedOutputStream = new BufferedOutputStream(outputStream);
                TcpUi.printMessage("单片机地址: " + client.getRemoteSocketAddress() + "   OutputSteamHashcode: " + bufferedOutputStream.hashCode());
                new ReceiveThread(bufferedInputStream, bufferedOutputStream).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

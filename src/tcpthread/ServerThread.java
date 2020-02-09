package tcpthread;

import ui.TcpUI;

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
            TcpUI ui = new TcpUI();
            ui.initUi();
            ui.printMessage("server established, waiting for client connect...");
            while (true) {
                Socket client = server.accept();
                ui.printMessage("client connected");
                inputStream = client.getInputStream();
                bufferedInputStream = new BufferedInputStream(inputStream);
                outputStream = client.getOutputStream();
                bufferedOutputStream = new BufferedOutputStream(outputStream);
                ui.printMessage("client address: " + client.getRemoteSocketAddress() + "   bos_code: " + bufferedOutputStream.hashCode());
                new ReceiveThread(bufferedInputStream, bufferedOutputStream, ui).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

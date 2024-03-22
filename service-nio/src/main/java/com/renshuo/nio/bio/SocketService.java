package com.renshuo.nio.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Lenovo on 2023/6/19.
 */
public class SocketService {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(9001);

        while (true){
            System.out.println("等待连接");

            Socket clientSocket = server.accept();
            System.out.println("链接成功了");
//            handle(clientSocket);

            new Thread(()->{
                try {
                    handle(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }

    private static void handle(Socket clientSocket) throws IOException {

        byte [] bytes = new byte[1024];
        int read =clientSocket.getInputStream().read(bytes);
        if(read !=-1){
            System.out.println("接收客户端数据:"+new String(bytes,0,read));
        }
        clientSocket.getOutputStream().write("你好客户端".getBytes());
        clientSocket.getOutputStream().flush();

    }
}

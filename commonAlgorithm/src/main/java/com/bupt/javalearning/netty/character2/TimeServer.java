package com.bupt.javalearning.netty.character2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by hujun on 2016/9/4.
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if(args!=null&&args.length>0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }

        System.out.println("The listen port is " + port);

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("The time server starts in the port: " + port);
            Socket socket = null;
            while (true){
                socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(serverSocket!=null){
                try {
                    System.out.println("The server is closed");
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}

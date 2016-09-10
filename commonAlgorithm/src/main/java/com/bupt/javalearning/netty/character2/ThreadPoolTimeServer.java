package com.bupt.javalearning.netty.character2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by hujun on 2016/9/4.
 */
public class ThreadPoolTimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if(args!=null&&args.length>0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server starts in the port: " + port);
            Socket socket = null;
            TimeServerHandlerExecutePool singleExecutor  = new TimeServerHandlerExecutePool(50,10000);
            while (true){
                socket = server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(server!=null){
                System.out.println("The time server close");
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

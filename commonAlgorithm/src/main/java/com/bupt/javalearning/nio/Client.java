package com.bupt.javalearning.nio;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by hujun on 16/8/10.
 */
public class Client {

    private static final Logger logger = Logger.getLogger(TCProtocol.class);

    private static int ALLOCATE_SIZE = 1024;
    private String host;
    private int port;
    private SocketChannel socketChannel;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        InetSocketAddress address = new InetSocketAddress(host,port);
        try {
            socketChannel = SocketChannel.open(address);
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendData(String data){
        //TODO 超过ALLOCATE_SIZE如何处理
        ByteBuffer bf = ByteBuffer.allocate(ALLOCATE_SIZE);
        try {
            socketChannel.write(ByteBuffer.wrap(data.getBytes()));
            int iterCount = 0;
            while (true){
                bf.clear();
                int readBytes = socketChannel.read(bf);
                if(readBytes>0){
                    bf.flip();
                    logger.info("Client data is " + new String(bf.array(),0,readBytes));
                    break;
                }
                logger.info("the iterCount is "+iterCount);
                iterCount++;
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e1){
            e1.printStackTrace();
        }

    }

    public void clientRegister(){
        logger.info("client register to server");
        Message msg = Message.REGISTER;
        this.sendData(msg.getName());
    }

    public static void main(String[] args) {
        String host = "localhost";
        int port = 65432;
        System.out.println("Client begin to send");
        Client client = new Client(host,port);
        String requestData = "First send data";

        client.sendData(requestData);

        client.clientRegister();

    }

}

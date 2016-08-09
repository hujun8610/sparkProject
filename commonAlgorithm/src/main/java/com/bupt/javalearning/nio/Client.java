package com.bupt.javalearning.nio;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
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
            while (true){
                bf.clear();
                int readBytes = socketChannel.read(bf);
                if(readBytes>0){
                    bf.flip();
                    logger.info("Client data is " + new String(bf.array(),0,readBytes));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String host = "localhost";
        int port = 65432;
        Client client = new Client(host,port);
        String requestData = "First send data";
        client.sendData(requestData);

    }

}

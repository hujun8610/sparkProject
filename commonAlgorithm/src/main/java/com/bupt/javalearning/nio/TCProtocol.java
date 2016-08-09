package com.bupt.javalearning.nio;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by hujun on 2016/8/8.
 */
public class TCProtocol implements Protocol{
    private static int ALLOCATE_SIZE = 1024;

    @Override
    public void handleRead(SelectionKey key) {
        ByteBuffer bf = ByteBuffer.allocate(ALLOCATE_SIZE);
        SocketChannel socketChannel = (SocketChannel) key.channel();
        try {
            while (true){
                int readBytes = socketChannel.read(bf);
                if(readBytes>0){
                    //Todo read data from bf
                    String data = new String(bf.array(),0,readBytes);
                    System.out.println(data);
                    bf.clear();
                }else{
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void handleWrite(SelectionKey key) {

    }

    @Override
    public void handleAccepted(SelectionKey key) {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
        try {
            SocketChannel socket = serverSocketChannel.accept();
            socket.configureBlocking(false);
            socket.register(key.selector(),SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

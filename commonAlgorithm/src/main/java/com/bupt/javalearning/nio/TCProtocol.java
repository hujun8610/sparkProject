package com.bupt.javalearning.nio;


import org.apache.log4j.Logger;

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

    private static final Logger logger = Logger.getLogger(TCProtocol.class);


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
                    logger.info(data);
                    bf.clear();
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void handleWrite(SelectionKey key) {
        ByteBuffer bf = (ByteBuffer) key.attachment();
        bf.flip();
        SocketChannel  socketChannel = (SocketChannel) key.channel();
        try {
            socketChannel.write(bf);
            while (bf.hasRemaining()){
                key.interestOps(SelectionKey.OP_READ);
            }
            bf.compact();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

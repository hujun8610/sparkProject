package com.bupt.javalearning.nio;

import java.nio.channels.SelectionKey;

/**
 * Created by hujun on 2016/8/8.
 */
public interface Protocol {
    /**
     *  get the client SocketChannel;
     *  read from the socket;
     * */
    public void handleRead(SelectionKey key);

    /**
     * get the client SocketChannel;
     * write on the socket
     * */
    public void handleWrite(SelectionKey key);

    /**
     *  get the client SocketChannel;
     *  associate that SocketChannel  to the Selector;
     *  record it for read/write operations
     * */
    public void handleAccepted(SelectionKey key);

}

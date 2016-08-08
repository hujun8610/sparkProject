package com.bupt.javalearning.nio;

import java.nio.channels.SelectionKey;

/**
 * Created by hujun on 2016/8/8.
 */
public interface Protocol {
    public void handleRead(SelectionKey key);
    public void handleWrite(SelectionKey key);
    public void handleAccepted(SelectionKey key);

}
